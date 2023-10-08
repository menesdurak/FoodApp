package com.menesdurak.foodapp.domain.repository

import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.data.remote.dto.CartResponse
import com.menesdurak.foodapp.data.remote.dto.FoodsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field

interface FoodRepository {
    suspend fun getFoods(): Flow<NetworkResponseState<FoodsResponse>>
    suspend fun postFoodsToCart(
        foodName: String,
        image: String,
        price: Int,
        count: Int,
        userName: String,
    ): Flow<NetworkResponseState<CartResponse>>
}