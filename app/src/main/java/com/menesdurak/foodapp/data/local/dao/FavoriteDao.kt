package com.menesdurak.foodapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.menesdurak.foodapp.data.local.entity.FavoriteFood

@Dao
interface FavoriteDao {

    @Insert
    suspend fun insertFood(food: FavoriteFood)

    @Update
    suspend fun updateFavorite(food: FavoriteFood)

    @Delete
    suspend fun deleteFavorite(food: FavoriteFood)

    @Query("SELECT * FROM favorites_table")
    suspend fun getAllFavorites(): List<FavoriteFood>

}