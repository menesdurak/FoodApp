package com.menesdurak.foodapp.presentation.detail

import com.menesdurak.foodapp.data.remote.dto.Response

sealed class DetailUiState<out T> {
    object Loading : DetailUiState<Nothing>()
    data class Success<T>(val data: T) : DetailUiState<T>()
    data class Error(val message: String) : DetailUiState<Nothing>()

}