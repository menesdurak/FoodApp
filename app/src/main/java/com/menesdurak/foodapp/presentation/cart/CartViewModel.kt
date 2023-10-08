package com.menesdurak.foodapp.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.domain.usecase.PostFoodsToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val postFoodsToCartUseCase: PostFoodsToCartUseCase
): ViewModel() {

    private val _cartUiState = MutableLiveData<CartUiState>()
    val cartUiState: LiveData<CartUiState> get() = _cartUiState

    fun postFoodsToCart(
        foodName: String,
        image: String,
        price: Int,
        count: Int,
        userName: String,
    ) {
        viewModelScope.launch {
            postFoodsToCartUseCase(foodName, image, price, count, userName).collectLatest {
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