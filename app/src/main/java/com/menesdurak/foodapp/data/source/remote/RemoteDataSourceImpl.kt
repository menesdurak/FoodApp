package com.menesdurak.foodapp.data.source.remote

import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.data.remote.api.FoodApi
import com.menesdurak.foodapp.data.remote.dto.CartResponse
import com.menesdurak.foodapp.data.remote.dto.Response
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

    override suspend fun postFoodsToCart(
        foodName: String,
        image: String,
        price: Int,
        count: Int,
        userName: String,
    ): NetworkResponseState<Response> =
        try {
            val response = foodApi.postFoodsToCart(foodName, image, price, count, userName)
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }

    override suspend fun getFoodsFromCart(userName: String): NetworkResponseState<CartResponse> =
        try {
            val response = foodApi.getFoodsFromCart(userName)
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }

    override suspend fun deleteFoodFromCart(
        foodId: Int,
        userName: String,
    ): NetworkResponseState<Response> =
        try {
            val response = foodApi.deleteFoodFromCart(foodId, userName)
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }

}