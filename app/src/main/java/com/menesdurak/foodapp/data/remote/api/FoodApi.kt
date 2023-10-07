package com.menesdurak.foodapp.data.remote.api

import com.menesdurak.foodapp.data.remote.dto.FoodsResponse
import retrofit2.http.GET

interface FoodApi {

    @GET("tumYemekleriGetir.php")
    suspend fun getAllFoods(): FoodsResponse
}