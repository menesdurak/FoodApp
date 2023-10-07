package com.menesdurak.foodapp.di.repository

import com.menesdurak.foodapp.data.repository.FoodRepositoryImpl
import com.menesdurak.foodapp.domain.repository.FoodRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FoodRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFoodRepository(foodRepositoryImpl: FoodRepositoryImpl): FoodRepository
}