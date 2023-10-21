package com.menesdurak.foodapp.domain.repository

import com.menesdurak.foodapp.data.LocalResponseState
import com.menesdurak.foodapp.data.local.entity.FavoriteFood
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun insertFood(food: FavoriteFood)
    suspend fun updateFavorite(food: FavoriteFood)
    suspend fun deleteFavorite(food: FavoriteFood)
    suspend fun getAllFavorites(): Flow<LocalResponseState<List<FavoriteFood>>>
    suspend fun getAllFavoritesId(): Flow<LocalResponseState<List<Int>>>
}