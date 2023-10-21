package com.menesdurak.foodapp.data.remote.dto

data class CartFoodUi(
    val ids: List<String>,
    val foodName: String,
    val image: String,
    val price: String,
    val count: Int,
    var totalPrice: Int = count * price.toInt(),
    val userName: String,
)
