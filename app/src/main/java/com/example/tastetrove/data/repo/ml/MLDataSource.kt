package com.example.tastetrove.data.repo.ml

import android.util.Log
import com.example.tastetrove.data.model.api.ml.MLFoodResponse
import com.example.tastetrove.data.network.Resource
import com.example.tastetrove.data.retrofit.MLFoodApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject


class MLDataSource @Inject constructor(
    private val apiService: MLFoodApiService
) {

    suspend fun analyze(
        imageFile: File,
    ): Flow<Resource<MLFoodResponse>> = flow {
        try {
            emit(Resource.Loading())

            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "file",
                imageFile.name,
                requestImageFile
            )

            val request = apiService.analyze(
                file = multipartBody
            )

            val response = request.body()
            if (request.isSuccessful) {
                emit(Resource.Success(response ?: MLFoodResponse()))
            } else {
                emit(Resource.Error(request.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}