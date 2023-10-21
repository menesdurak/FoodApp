package com.menesdurak.foodapp.data.source.local

import com.menesdurak.foodapp.data.LocalResponseState
import com.menesdurak.foodapp.data.local.entity.FavoriteFood

interface LocalDataSource {
    suspend fun insertFood(food: FavoriteFood)
    suspend fun updateFavorite(food: FavoriteFood)
    suspend fun deleteFavorite(food: FavoriteFood)
    suspend fun getAllFavorites(): LocalResponseState<List<FavoriteFood>>
    suspend fun getAllFavoritesId(): LocalResponseState<List<Int>>
}