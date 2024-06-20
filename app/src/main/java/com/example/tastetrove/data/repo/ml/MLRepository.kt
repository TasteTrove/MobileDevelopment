package com.example.tastetrove.data.repo.ml

import com.example.tastetrove.data.model.api.ml.MLFoodResponse
import com.example.tastetrove.data.network.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface MLRepository {

    suspend fun analyze(
        imageFile: File,
    ): Flow<Resource<MLFoodResponse>>

}