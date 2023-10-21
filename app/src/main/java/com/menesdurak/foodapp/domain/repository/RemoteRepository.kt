package com.menesdurak.foodapp.domain.repository

import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.data.remote.dto.CartResponse
import com.menesdurak.foodapp.data.remote.dto.Food
import com.menesdurak.foodapp.data.remote.dto.Response
import com.menesdurak.foodapp.data.remote.dto.FoodsResponse
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getFoods(): Flow<NetworkResponseState<FoodsResponse>>
    suspend fun postFoodsToCart(
        foodName: String,
        image: String,
        price: Int,
        count: Int,
        userName: String,
    ): Flow<NetworkResponseState<Response>>
    suspend fun getFoodsFromCart(userName: String): Flow<NetworkResponseState<CartResponse>>
    suspend fun deleteFoodFromCart(foodId: Int, userName: String): Flow<NetworkResponseState<Response>>
    suspend fun searchFoods(word: String): Flow<NetworkResponseState<List<Food>>>
}