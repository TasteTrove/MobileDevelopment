package com.example.tastetrove.data.retrofit

import com.example.tastetrove.data.response.auth.LoginResponse
import com.example.tastetrove.data.response.auth.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("email") name: String,
        @Field("password") email: String,
        @Field("nama") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse




}