package com.menesdurak.foodapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class FavoriteFood(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val foodName: String,
    val image: String,
    val price: String,
    val count: Int,
    var totalPrice: Int = count * price.toInt(),
)
