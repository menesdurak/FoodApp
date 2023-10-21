package com.menesdurak.foodapp.presentation.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.data.local.entity.FavoriteFood
import com.menesdurak.foodapp.data.local.mapper.FavoriteToFoodUiMapper
import com.menesdurak.foodapp.databinding.FragmentFavoriteBinding
import com.menesdurak.foodapp.presentation.cart.CartUiState
import com.menesdurak.foodapp.presentation.cart.CartViewModel
import com.menesdurak.foodapp.presentation.home.FoodAdapter
import com.menesdurak.foodapp.presentation.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private val cartViewModel by viewModels<CartViewModel>()
    private val loginViewModel by viewModels<LoginViewModel>()
    private var userName = ""
    private val favoriteAdapter by lazy { FavoriteAdapter(::onItemClick, ::onFavoriteClick) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        userName = loginViewModel.getUserMail()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteViewModel.getAllFavoriteFoods()

        with(binding.recyclerView) {
            adapter = favoriteAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)

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

    override fun onResume() {
        super.onResume()

        observeUiState()
    }

    private fun observeUiState() {
        favoriteViewModel.favoriteUiState.observe(viewLifecycleOwner) {
            when (it) {
                is FavoriteUiState.Error -> {
                    Log.e("FavoriteViewModel", "Error: ${it.exception}")
                }

                FavoriteUiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is FavoriteUiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView
                    favoriteAdapter.updateFavorites(it.data)
                }
            }
        }
    }

    private fun onFavoriteClick(position: Int, favoriteFood: FavoriteFood) {
        Snackbar.make(
            requireView(),
            "Do you want to remove ${favoriteFood.foodName} from your favorites?",
            Snackbar.LENGTH_SHORT
        ).setAction("YES") {
            favoriteAdapter.removeFavorite(position)
            favoriteViewModel.deleteFood(favoriteFood)
        }.show()
    }

    private fun onItemClick(favoriteFood: FavoriteFood) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(
            FavoriteToFoodUiMapper().map(favoriteFood)
        )
        findNavController().navigate(action)
    }

}