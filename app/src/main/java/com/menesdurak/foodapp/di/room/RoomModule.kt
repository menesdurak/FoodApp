package com.menesdurak.foodapp.di.room

import android.content.Context
import com.menesdurak.foodapp.data.local.dao.FavoriteDao
import com.menesdurak.foodapp.data.local.database.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideFavoriteDatabase(@ApplicationContext context: Context): FavoriteDatabase {
        return FavoriteDatabase.getFavoriteDatabase(context)
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase): FavoriteDao {
        return favoriteDatabase.getFavoriteDao()
    }
}