package com.menesdurak.foodapp.di.network

import com.menesdurak.foodapp.data.remote.api.FoodApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FoodNetworkModule {

    private const val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"

    @Provides
    @Singleton
    fun provideFoodApi() : FoodApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodApi::class.java)
    }
}