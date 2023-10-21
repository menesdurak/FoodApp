package com.menesdurak.foodapp.data

sealed class LocalResponseState<out T : Any> {
    object Loading : LocalResponseState<Nothing>()
    data class Success<out T : Any>(val result: T?) : LocalResponseState<T>()
    data class Error(val exception: Exception) : LocalResponseState<Nothing>()
}