package com.example.tastetrove.data.di

import android.content.Context
import com.example.tastetrove.data.pref.UserPreference
import com.example.tastetrove.data.pref.dataStore
import com.example.tastetrove.data.repo.UserRepository
import com.example.tastetrove.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.ApiService(context, user.token)
        return UserRepository.getInstance(pref, apiService)
    }
}