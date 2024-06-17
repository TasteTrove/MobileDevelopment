package com.example.tastetrove.di

import android.content.Context
import com.example.tastetrove.data.dao.FavoriteFoodDao
import com.example.tastetrove.data.dao.HistoryDao
import com.example.tastetrove.data.local.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FoodDatabase = FoodDatabase.getInstance(context)

    @Provides
    fun provideHistoryDao(database: FoodDatabase): HistoryDao = database.historyDao()

    @Provides
    fun provideFavoriteFoodDao(database: FoodDatabase): FavoriteFoodDao = database.favoritesFoodDao()

}