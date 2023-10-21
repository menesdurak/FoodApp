package com.menesdurak.foodapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.menesdurak.foodapp.data.local.dao.FavoriteDao
import com.menesdurak.foodapp.data.local.entity.FavoriteFood


@Database(entities = [FavoriteFood::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        fun getFavoriteDatabase(context: Context): FavoriteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "favorite_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}