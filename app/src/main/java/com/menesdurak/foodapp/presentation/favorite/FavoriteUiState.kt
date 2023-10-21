package com.menesdurak.foodapp.presentation.favorite

import com.menesdurak.foodapp.data.local.entity.FavoriteFood

sealed class FavoriteUiState {
    object Loading : FavoriteUiState()
    data class Success(val data: List<FavoriteFood>) : FavoriteUiState()
    data class Error(val exception: Exception) : FavoriteUiState()

}