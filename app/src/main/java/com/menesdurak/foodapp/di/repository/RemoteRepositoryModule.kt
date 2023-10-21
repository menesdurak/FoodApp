package com.menesdurak.foodapp.di.repository

import com.menesdurak.foodapp.data.repository.RemoteRepositoryImpl
import com.menesdurak.foodapp.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFoodRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository
}