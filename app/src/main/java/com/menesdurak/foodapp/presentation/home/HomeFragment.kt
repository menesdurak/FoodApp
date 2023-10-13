package com.menesdurak.foodapp.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.data.remote.dto.Food
import com.menesdurak.foodapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private val homeAdapter by lazy { FoodAdapter(::onItemClick) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
            .menu.getItem(0).isChecked = true

        homeViewModel.getFoods()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.recyclerView) {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
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
                }
            }
        }
    }

    private fun onItemClick(food: Food) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(food)
        findNavController().navigate(action)
    }

}