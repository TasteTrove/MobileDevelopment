package com.example.tastetrove.data.retrofit

import com.example.tastetrove.data.model.api.ml.MLFoodResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MLFoodApiService {

    @Multipart
    @POST("/")
    suspend fun analyze(
        @Part file: MultipartBody.Part,
    ): Response<MLFoodResponse>

}