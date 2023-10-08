package com.menesdurak.foodapp.data.source

import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.data.remote.dto.CartResponse
import com.menesdurak.foodapp.data.remote.dto.FoodsResponse
import retrofit2.http.Field

interface RemoteDataSource {

    suspend fun getAllFoods(): NetworkResponseState<FoodsResponse>
    suspend fun postFoodsToCart(
        foodName: String,
        image: String,
        price: Int,
        count: Int,
        userName: String,
    ): NetworkResponseState<CartResponse>
}