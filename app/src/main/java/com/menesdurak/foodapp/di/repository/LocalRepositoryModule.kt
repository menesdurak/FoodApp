package com.menesdurak.foodapp.di.repository

import com.menesdurak.foodapp.data.repository.LocalRepositoryImpl
import com.menesdurak.foodapp.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl,
    ): LocalRepository
}