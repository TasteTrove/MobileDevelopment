package com.example.tastetrove.data.retrofit

import com.example.tastetrove.data.response.FoodResponse
import com.example.tastetrove.data.response.auth.LoginResponse
import com.example.tastetrove.data.response.auth.RegisterResponse
import com.example.tastetrove.BuildConfig
import com.example.tastetrove.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("/auth/register")
    suspend fun register(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("/auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("food")
    suspend fun getFoods() : FoodResponse

    @Headers("Authorization: Bearer ${BuildConfig.BASE_URL}")
    @GET("user/{nama}")
    fun getUser(
        @Path("nama") username: String
    ) : Call<UserResponse>


    @Headers("Authorization: token ${BuildConfig.BASE_URL}")
    @GET("search/users")
    fun getSearch(
        @Query("q") q: String
    ) : Call <FoodResponse>
}