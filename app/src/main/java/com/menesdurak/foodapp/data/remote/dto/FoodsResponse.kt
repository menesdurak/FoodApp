package com.menesdurak.foodapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FoodsResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("yemekler")
    val foods: List<Food>
)