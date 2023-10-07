package com.menesdurak.foodapp.domain.repository

import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.data.remote.dto.FoodsResponse
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    suspend fun getFoods(): Flow<NetworkResponseState<FoodsResponse>>
}