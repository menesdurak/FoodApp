package com.menesdurak.foodapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("yemek_adi")
    val name: String?,
    @SerializedName("yemek_fiyat")
    val price: String?,
    @SerializedName("yemek_id")
    val id: String?,
    @SerializedName("yemek_resim_adi")
    val image: String?
)