package com.menesdurak.foodapp.presentation.cart

import com.menesdurak.foodapp.data.remote.dto.Response

sealed class CartUiState<out T> {
    object Loading : CartUiState<Nothing>()
    data class Success<T>(val data: T) : CartUiState<T>()
    data class Error(val message: String) : CartUiState<Nothing>()

}