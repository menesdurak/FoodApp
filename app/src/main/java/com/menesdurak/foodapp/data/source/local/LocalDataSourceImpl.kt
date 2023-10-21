package com.menesdurak.foodapp.data.source.local

import com.menesdurak.foodapp.data.LocalResponseState
import com.menesdurak.foodapp.data.local.dao.FavoriteDao
import com.menesdurak.foodapp.data.local.entity.FavoriteFood
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val favoriteDao: FavoriteDao) :
    LocalDataSource {
    override suspend fun insertFood(food: FavoriteFood) {
        favoriteDao.insertFood(food)
    }

    override suspend fun updateFavorite(food: FavoriteFood) {
        favoriteDao.updateFavorite(food)
    }

    override suspend fun deleteFavorite(food: FavoriteFood) {
        val newFavoriteFood = FavoriteFood(food.foodId, "", "", "")
        favoriteDao.deleteFavorite(newFavoriteFood)
    }

    override suspend fun getAllFavorites(): LocalResponseState<List<FavoriteFood>> {
        return try {
            val response = favoriteDao.getAllFavorites()
            LocalResponseState.Success(response)
        } catch (e: Exception) {
            LocalResponseState.Error(e)
        }
    }

    override suspend fun getAllFavoritesId(): LocalResponseState<List<Int>> {
        return try {
            val response = favoriteDao.getAllFavoritesId()
            LocalResponseState.Success(response)
        } catch (e: Exception) {
            LocalResponseState.Error(e)
        }
    }
}