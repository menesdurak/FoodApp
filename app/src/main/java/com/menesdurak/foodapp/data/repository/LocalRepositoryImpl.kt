package com.menesdurak.foodapp.data.repository

import android.util.Log
import com.menesdurak.foodapp.data.LocalResponseState
import com.menesdurak.foodapp.data.local.entity.FavoriteFood
import com.menesdurak.foodapp.data.source.local.LocalDataSource
import com.menesdurak.foodapp.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    LocalRepository {
    override suspend fun insertFood(food: FavoriteFood) {
        localDataSource.insertFood(food)
    }

    override suspend fun updateFavorite(food: FavoriteFood) {
        localDataSource.updateFavorite(food)
    }

    override suspend fun deleteFavorite(food: FavoriteFood) {
        localDataSource.deleteFavorite(food)
    }

    override suspend fun getAllFavorites(): Flow<LocalResponseState<List<FavoriteFood>>> {
        return flow {
            emit(LocalResponseState.Loading)
            when (val response = localDataSource.getAllFavorites()) {
                is LocalResponseState.Success -> {
                    emit(LocalResponseState.Success(response.result))
                }

                is LocalResponseState.Error -> {
                    emit(LocalResponseState.Error(response.exception))
                }

                else -> {
                    Log.e("Error", "Ne yaptin dostum?")
                }
            }
        }
    }
}