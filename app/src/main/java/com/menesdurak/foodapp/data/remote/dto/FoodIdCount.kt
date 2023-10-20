package com.menesdurak.foodapp.data.remote.dto

data class FoodIdCount (
    var ids: List<String>,
    var count: Int,
    val image: String,
    val userName: String,
    val price: String,
    var totalPrice: Int = count * price.toInt()
)