package com.menesdurak.foodapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CartFood(
    @SerializedName("sepet_yemek_id") val foodId: String,
    @SerializedName("yemek_adi") val foodName: String,
    @SerializedName("yemek_resim_adi") val image: String,
    @SerializedName("yemek_fiyat") val price: String,
    @SerializedName("yemek_siparis_adet") val count: String,
    @SerializedName("kullanici_adi") val userName: String,
)
