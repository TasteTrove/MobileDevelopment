package com.example.tastetrove.data.retrofit

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.tastetrove.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    inline fun <reified T> apiService(context: Context, token: String = ""): T {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            )

        val client = if (token != "") {
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(requestHeaders)
            }

            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .addInterceptor(ChuckerInterceptor(context))
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(ChuckerInterceptor(context))
                .build()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(T::class.java)
    }

    inline fun <reified T> getApiService(context: Context, baseUrl: String): T {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ChuckerInterceptor(context))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(T::class.java)
    }

}