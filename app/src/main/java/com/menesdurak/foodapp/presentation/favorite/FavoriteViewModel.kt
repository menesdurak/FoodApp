package com.menesdurak.foodapp.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.foodapp.data.LocalResponseState
import com.menesdurak.foodapp.data.local.entity.FavoriteFood
import com.menesdurak.foodapp.domain.usecase.DeleteFavoriteFoodUseCase
import com.menesdurak.foodapp.domain.usecase.GetAllFavoriteFoodsIdUseCase
import com.menesdurak.foodapp.domain.usecase.GetAllFavoriteFoodsUseCase
import com.menesdurak.foodapp.domain.usecase.InsertFavoriteFoodUseCase
import com.menesdurak.foodapp.domain.usecase.UpdateFavoriteFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val insertFavoriteFoodUseCase: InsertFavoriteFoodUseCase,
    private val updateFavoriteFoodUseCase: UpdateFavoriteFoodUseCase,
    private val deleteFavoriteFoodUseCase: DeleteFavoriteFoodUseCase,
    private val getAllFavoriteFoodsUseCase: GetAllFavoriteFoodsUseCase,
    private val getAllFavoriteFoodsIdUseCase: GetAllFavoriteFoodsIdUseCase
): ViewModel() {

    private val _favoriteUiState = MutableLiveData<FavoriteUiState>()
    val favoriteUiState: LiveData<FavoriteUiState> = _favoriteUiState

    private val _favoriteUiIdState = MutableLiveData<FavoriteUiIdState>()
    val favoriteUiIdState: LiveData<FavoriteUiIdState> = _favoriteUiIdState

    fun getAllFavoriteFoods() {
        viewModelScope.launch {
            getAllFavoriteFoodsUseCase().collectLatest {
                when (it) {
                    is LocalResponseState.Error -> {
                        _favoriteUiState.postValue(FavoriteUiState.Error(it.exception))
                    }
                    LocalResponseState.Loading -> {
                        _favoriteUiState.postValue(FavoriteUiState.Loading)
                    }
                    is LocalResponseState.Success -> {
                        _favoriteUiState.postValue(FavoriteUiState.Success(it.result!!))
                    }
                }
            }
        }
    }

    fun getAllFavoriteFoodsId() {
        viewModelScope.launch {
            getAllFavoriteFoodsIdUseCase().collectLatest {
                when (it) {
                    is LocalResponseState.Error -> {
                        _favoriteUiIdState.postValue(FavoriteUiIdState.Error(it.exception))
                    }
                    LocalResponseState.Loading -> {}
                    is LocalResponseState.Success -> {
                        _favoriteUiIdState.postValue(FavoriteUiIdState.Success(it.result!!))
                    }
                }
            }
        }
    }

    fun insertFood(food: FavoriteFood) {
        viewModelScope.launch {
            insertFavoriteFoodUseCase(food)
        }
    }

    fun updateFood(food: FavoriteFood) {
        viewModelScope.launch {
            updateFavoriteFoodUseCase(food)
        }
    }

    fun deleteFood(food: FavoriteFood) {
        viewModelScope.launch {
            deleteFavoriteFoodUseCase(food)
        }
    }

}