package com.menesdurak.foodapp.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.foodapp.common.convertToCartFoodUi
import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.data.remote.dto.CartFood
import com.menesdurak.foodapp.data.remote.dto.CartFoodUi
import com.menesdurak.foodapp.data.remote.dto.CartResponse
import com.menesdurak.foodapp.data.remote.dto.Food
import com.menesdurak.foodapp.data.remote.dto.Response
import com.menesdurak.foodapp.domain.usecase.DeleteFoodFromCartUseCase
import com.menesdurak.foodapp.domain.usecase.GetFoodsFromCartUseCase
import com.menesdurak.foodapp.domain.usecase.PostFoodsToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getFoodsFromCartUseCase: GetFoodsFromCartUseCase,
    private val deleteFoodFromCartUseCase: DeleteFoodFromCartUseCase
): ViewModel() {

    private val _cartUiState = MutableLiveData<CartUiState<List<CartFoodUi>>>()
    val cartUiState: LiveData<CartUiState<List<CartFoodUi>>> get() = _cartUiState

    private val _cartUiState2 = MutableLiveData<CartUiState<Response>>()

    fun getFoodsFromCart(userName: String) {
        viewModelScope.launch {
            getFoodsFromCartUseCase(userName).collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> {
                        _cartUiState.postValue(CartUiState.Success(it.result!!.cartFoodList.convertToCartFoodUi()))
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

    fun deleteFoodFromCart(foodId: Int, userName: String) {
        viewModelScope.launch {
            deleteFoodFromCartUseCase(foodId, userName).collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> {
                        _cartUiState2.postValue(CartUiState.Success(it.result!!))
                    }

                    NetworkResponseState.Loading -> {
                        _cartUiState2.postValue(CartUiState.Loading)
                    }

                    is NetworkResponseState.Error -> {
                        _cartUiState2.postValue(CartUiState.Error("Error"))
                    }
                }
            }
        }
    }
}