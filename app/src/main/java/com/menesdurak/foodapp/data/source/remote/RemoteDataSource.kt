package com.menesdurak.foodapp.data.source.remote

import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.data.remote.dto.CartResponse
import com.menesdurak.foodapp.data.remote.dto.Response
import com.menesdurak.foodapp.data.remote.dto.FoodsResponse

interface RemoteDataSource {

    suspend fun getAllFoods(): NetworkResponseState<FoodsResponse>
    suspend fun postFoodsToCart(
        foodName: String,
        image: String,
        price: Int,
        count: Int,
        userName: String,
    ): NetworkResponseState<Response>
    suspend fun getFoodsFromCart(userName: String): NetworkResponseState<CartResponse>
    suspend fun deleteFoodFromCart(foodId: Int, userName: String): NetworkResponseState<Response>
}