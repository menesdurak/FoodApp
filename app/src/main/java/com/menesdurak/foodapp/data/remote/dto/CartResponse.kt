package com.menesdurak.foodapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("success")
    val success: Int?,
    @SerializedName("message")
    val message: String?
)
