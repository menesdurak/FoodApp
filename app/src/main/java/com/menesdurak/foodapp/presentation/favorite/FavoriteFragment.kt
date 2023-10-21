package com.menesdurak.foodapp.presentation.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.data.local.entity.FavoriteFood
import com.menesdurak.foodapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        favoriteViewModel.getAllFavoriteFoods()

        favoriteViewModel.favoriteUiState.observe(viewLifecycleOwner) {
            when (it) {
                is FavoriteUiState.Error -> {
                    Log.e("FavoriteViewModel", "Error: ${it.exception}")
                }
                FavoriteUiState.Loading -> {

                }
                is FavoriteUiState.Success -> {
                    println(it.data)
                }
            }
        }

        return binding.root
    }

}