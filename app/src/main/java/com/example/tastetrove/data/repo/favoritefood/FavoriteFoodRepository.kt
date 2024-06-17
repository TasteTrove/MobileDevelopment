package com.example.tastetrove.data.repo.favoritefood

import com.example.tastetrove.data.model.FavoriteFoodModel
import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.data.network.Resource
import kotlinx.coroutines.flow.Flow


interface FavoriteFoodRepository {

    suspend fun getData(): Flow<Resource<List<FavoriteFoodModel>>>

    suspend fun insertData(data: FavoriteFoodModel): Flow<Resource<FavoriteFoodModel>>

}