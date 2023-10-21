package com.menesdurak.foodapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class FavoriteFood(
    @PrimaryKey
    val foodId: Int,
    val foodName: String,
    val image: String,
    val price: String
)
