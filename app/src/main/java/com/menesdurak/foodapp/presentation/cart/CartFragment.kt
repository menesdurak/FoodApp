package com.menesdurak.foodapp.presentation.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.databinding.FragmentCartBinding
import com.menesdurak.foodapp.presentation.home.HomeUiState
import com.menesdurak.foodapp.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private val cartViewModel by viewModels<CartViewModel>()
    private var userName = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        val bundle: CartFragmentArgs by navArgs()
        userName = bundle.userName

        cartViewModel.getFoodsFromCart(userName)
        observeUiState()

        return binding.root
    }

    private fun observeUiState() {
        cartViewModel.cartUiState.observe(viewLifecycleOwner) {
            when (it) {
                is CartUiState.Error -> {
                    Log.e("HomeViewModel", "Error: ${it.message}")
                }

                CartUiState.Loading -> {
                    Log.e("HomeViewModel", "Loading")
                }

                is CartUiState.Success -> {
                    Log.e("HomeViewModel", "Success: ${it.data.cartFoodList}")
                }
            }
        }
    }

}