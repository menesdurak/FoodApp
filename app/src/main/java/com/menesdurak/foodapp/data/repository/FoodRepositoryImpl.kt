package com.menesdurak.foodapp.data.repository

import com.menesdurak.foodapp.data.NetworkResponseState
import com.menesdurak.foodapp.data.remote.dto.CartResponse
import com.menesdurak.foodapp.data.remote.dto.Food
import com.menesdurak.foodapp.data.remote.dto.Response
import com.menesdurak.foodapp.data.remote.dto.FoodsResponse
import com.menesdurak.foodapp.data.source.remote.RemoteDataSource
import com.menesdurak.foodapp.di.coroutine.IoDispatcher
import com.menesdurak.foodapp.domain.repository.FoodRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : FoodRepository {
    override suspend fun getFoods(): Flow<NetworkResponseState<FoodsResponse>> {
        return flow {
            emit(NetworkResponseState.Loading)
            when (val response = remoteDataSource.getAllFoods()) {
                is NetworkResponseState.Error -> {
                    emit(NetworkResponseState.Error(response.exception))
                }

                is NetworkResponseState.Success -> {
                    emit(NetworkResponseState.Success(response.result))
                }

                else -> {}
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun postFoodsToCart(
        foodName: String,
        image: String,
        price: Int,
        count: Int,
        userName: String,
    ): Flow<NetworkResponseState<Response>> {
        return flow {
            emit(NetworkResponseState.Loading)
            when (val response =
                remoteDataSource.postFoodsToCart(foodName, image, price, count, userName)) {
                is NetworkResponseState.Error -> {
                    emit(NetworkResponseState.Error(response.exception))
                }

                is NetworkResponseState.Success -> {
                    emit(NetworkResponseState.Success(response.result))
                }

                else -> {}
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getFoodsFromCart(userName: String): Flow<NetworkResponseState<CartResponse>> {
        return flow {
            emit(NetworkResponseState.Loading)
            when ( val response =
                remoteDataSource.getFoodsFromCart(userName)) {
                is NetworkResponseState.Error -> {
                    emit(NetworkResponseState.Error(response.exception))
                }

                is NetworkResponseState.Success -> {
                    emit(NetworkResponseState.Success(response.result))
                }

                else -> { }
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun deleteFoodFromCart(
        foodId: Int,
        userName: String,
    ): Flow<NetworkResponseState<Response>> {
        return flow {
            emit(NetworkResponseState.Loading)
            when ( val response =
                remoteDataSource.deleteFoodFromCart(foodId, userName)) {
                is NetworkResponseState.Error -> {
                    emit(NetworkResponseState.Error(response.exception))
                }

                is NetworkResponseState.Success -> {
                    emit(NetworkResponseState.Success(response.result))
                }

                else -> { }
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun searchFoods(word: String): Flow<NetworkResponseState<List<Food>>> {
        return flow {
            emit(NetworkResponseState.Loading)
            when (val response = remoteDataSource.getAllFoods()) {
                is NetworkResponseState.Error -> {
                    emit(NetworkResponseState.Error(response.exception))
                }

                is NetworkResponseState.Success -> {
                    emit(NetworkResponseState.Success(response.result?.foods))
                }

                else -> {}
            }
        }.flowOn(ioDispatcher)
    }

}