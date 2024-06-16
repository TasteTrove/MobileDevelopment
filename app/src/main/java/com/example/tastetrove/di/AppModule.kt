package com.example.tastetrove.di

import android.content.Context
import com.example.tastetrove.data.pref.UserPreference
import com.example.tastetrove.data.pref.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUserPref(@ApplicationContext context: Context): UserPreference =
        UserPreference.getInstance(context.dataStore)

}