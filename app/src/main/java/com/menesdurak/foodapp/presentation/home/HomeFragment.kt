package com.menesdurak.foodapp.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.data.remote.dto.Food
import com.menesdurak.foodapp.databinding.FragmentHomeBinding
import com.menesdurak.foodapp.presentation.cart.CartUiState
import com.menesdurak.foodapp.presentation.cart.CartViewModel
import com.menesdurak.foodapp.presentation.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private val cartViewModel by viewModels<CartViewModel>()
    private val loginViewModel by viewModels<LoginViewModel>()
    private var userName = ""
    private val homeAdapter by lazy { FoodAdapter(::onItemClick) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
            .menu.getItem(0).isChecked = true

        userName = loginViewModel.getUserMail()

        homeViewModel.getFoods()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.recyclerView) {
            adapter = homeAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        observeUiState()

    }

    private fun observeUiState() {
        homeViewModel.homeUiState.observe(viewLifecycleOwner) {
            when (it) {
                is HomeUiState.Error -> {
                    Log.e("HomeViewModel", "Error: ${it.message}")
                }

                HomeUiState.Loading -> {
                    Log.e("HomeViewModel", "Loading")
                }

                is HomeUiState.Success -> {
                    homeAdapter.updateFoods(it.data.foods)
                    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(query: String): Boolean {
                            homeAdapter.updateFoods(homeViewModel.filterFoods(query, it.data.foods))
                            return true
                        }

                        override fun onQueryTextChange(newText: String): Boolean {
                            homeAdapter.updateFoods(homeViewModel.filterFoods(newText, it.data.foods))
                            return false
                        }
                    })
                }
            }
        }
        if (userName != "") {
            cartViewModel.getFoodsFromCart(userName)
        }
        cartViewModel.cartUiState.observe(viewLifecycleOwner) {
            when (it) {
                is CartUiState.Error -> {
                    Log.e("HomeViewModel", "Error: ${it.message}")
                }

                CartUiState.Loading -> {
                }

                is CartUiState.Success -> {
                    val badge =
                        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
                            .getOrCreateBadge(R.id.cart)
                    badge.number = it.data.size
                    badge.isVisible = badge.number > 0
                }
            }
        }
    }

    private fun onItemClick(food: Food) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(food)
        findNavController().navigate(action)
    }

}