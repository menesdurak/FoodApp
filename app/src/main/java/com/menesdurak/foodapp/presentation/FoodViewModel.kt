package com.menesdurak.foodapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.domain.usecase.GetFoodsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val getFoodsUseCase: GetFoodsUseCase,
) : ViewModel() {

    private val _homeUiState = MutableLiveData<HomeUiState>()
    val homeUiState: LiveData<HomeUiState> get() = _homeUiState

    fun getFoods() {
        viewModelScope.launch {
            getFoodsUseCase().collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> {
                        _homeUiState.postValue(HomeUiState.Success(it.result!!))
                    }

                    NetworkResponseState.Loading -> {
                        _homeUiState.postValue(HomeUiState.Loading)
                    }

                    is NetworkResponseState.Error -> {
                        _homeUiState.postValue(HomeUiState.Error("Error"))
                    }
                }
            }
        }
    }
}