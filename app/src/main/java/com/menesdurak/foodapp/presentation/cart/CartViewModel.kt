package com.menesdurak.foodapp.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.data.remote.dto.CartResponse
import com.menesdurak.foodapp.data.remote.dto.Response
import com.menesdurak.foodapp.domain.usecase.GetFoodsFromCartUseCase
import com.menesdurak.foodapp.domain.usecase.PostFoodsToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getFoodsFromCartUseCase: GetFoodsFromCartUseCase
): ViewModel() {

    private val _cartUiState = MutableLiveData<CartUiState<CartResponse>>()
    val cartUiState: LiveData<CartUiState<CartResponse>> get() = _cartUiState

    fun getFoodsFromCart(userName: String) {
        viewModelScope.launch {
            getFoodsFromCartUseCase(userName).collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> {
                        _cartUiState.postValue(CartUiState.Success(it.result!!))
                    }

                    NetworkResponseState.Loading -> {
                        _cartUiState.postValue(CartUiState.Loading)
                    }

                    is NetworkResponseState.Error -> {
                        _cartUiState.postValue(CartUiState.Error("Error"))
                    }
                }
            }
        }
    }
}