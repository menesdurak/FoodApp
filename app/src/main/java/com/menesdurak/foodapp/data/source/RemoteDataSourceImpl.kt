package com.menesdurak.foodapp.data.source

import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.data.remote.api.FoodApi
import com.menesdurak.foodapp.data.remote.dto.FoodsResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val foodApi: FoodApi) : RemoteDataSource {
    override suspend fun getAllFoods(): NetworkResponseState<FoodsResponse> =
        try {
            val response = foodApi.getAllFoods()
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }

}