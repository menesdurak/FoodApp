package com.menesdurak.foodapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("sepet_yemekler") val cartFoodList: List<CartFood>,
    @SerializedName("success") val success: Int
)
