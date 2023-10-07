package com.menesdurak.foodapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val foodViewModel by viewModels<FoodViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        foodViewModel.getFoods()
        observeUiState()
    }

    private fun observeUiState() {
        foodViewModel.homeUiState.observe(this) {
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