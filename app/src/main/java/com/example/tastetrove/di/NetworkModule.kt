package com.example.tastetrove.di

import android.content.Context
import com.example.tastetrove.data.pref.UserPreference
import com.example.tastetrove.data.retrofit.ApiConfig
import com.example.tastetrove.data.retrofit.ApiService
import com.example.tastetrove.data.retrofit.MLFoodApiService
import com.example.tastetrove.data.retrofit.NewsApiService
import com.example.tastetrove.util.NewsUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providePrivateApiService(@ApplicationContext context: Context, userPreference: UserPreference): ApiService {
        val token = runBlocking { userPreference.getSession().first().token }
        return ApiConfig.apiService(context, token)
    }

    @Provides
    fun provideNewsApiService(@ApplicationContext context: Context): NewsApiService {
        return ApiConfig.getApiService(context, NewsUrl.BASE_URL)
    }

    @Provides
    fun provideMLFoodApiService(@ApplicationContext context: Context): MLFoodApiService {
        return ApiConfig.getApiService(context, "https://tastetrove-predict-v1-5ufiritwza-et.a.run.app")
    }

}