package com.menesdurak.foodapp.presentation

import androidx.annotation.StringRes
import com.menesdurak.foodapp.data.remote.dto.FoodsResponse

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: FoodsResponse) : HomeUiState()
    data class Error(val message: String) : HomeUiState()

}