package com.menesdurak.foodapp.presentation.cart

import com.menesdurak.foodapp.data.remote.dto.CartResponse

sealed class CartUiState {
    object Loading : CartUiState()
    data class Success(val data: CartResponse) : CartUiState()
    data class Error(val message: String) : CartUiState()

}