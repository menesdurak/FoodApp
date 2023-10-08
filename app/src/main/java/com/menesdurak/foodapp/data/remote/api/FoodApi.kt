package com.menesdurak.foodapp.data.remote.api

import com.menesdurak.foodapp.data.remote.dto.CartResponse
import com.menesdurak.foodapp.data.remote.dto.FoodsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodApi {

    @GET("tumYemekleriGetir.php")
    suspend fun getAllFoods(): FoodsResponse

    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun postFoodsToCart(
        @Field("yemek_adi") foodName: String,
        @Field("resim_adi") image: String,
        @Field("yemek_fiyat") price: Int,
        @Field("yemek_siparis_adet") count: Int,
        @Field("kullanici_adi") userName: String
    ): CartResponse
}