package com.menesdurak.foodapp.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.getFoods()
        observeUiState()

        return binding.root
    }

    private fun observeUiState() {
        homeViewModel.homeUiState.observe(viewLifecycleOwner) {
            when(it) {
                is HomeUiState.Error -> {
                    Log.e("HomeViewModel", "Error: ${it.message}")
                }
                HomeUiState.Loading -> {
                    Log.e("HomeViewModel", "Loading")
                }
                is HomeUiState.Success -> {
                    Log.e("HomeViewModel", "Success: ${it.data.foods}")
                }
            }
        }
    }

}