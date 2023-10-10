package com.menesdurak.foodapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.data.remote.dto.CartResponse
import com.menesdurak.foodapp.data.remote.dto.Response
import com.menesdurak.foodapp.domain.usecase.GetFoodsFromCartUseCase
import com.menesdurak.foodapp.domain.usecase.PostFoodsToCartUseCase
import com.menesdurak.foodapp.presentation.cart.CartUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val postFoodsToCartUseCase: PostFoodsToCartUseCase
): ViewModel() {

    private val _detailUiState = MutableLiveData<DetailUiState<Response>>()
    val detailUiState: LiveData<DetailUiState<Response>> get() = _detailUiState

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
                        _detailUiState.postValue(DetailUiState.Success(it.result!!))
                    }

                    NetworkResponseState.Loading -> {
                        _detailUiState.postValue(DetailUiState.Loading)
                    }

                    is NetworkResponseState.Error -> {
                        _detailUiState.postValue(DetailUiState.Error("Error"))
                    }
                }
            }
        }
    }

}