package com.example.tastetrove.data.repo.favoritefood

import com.example.tastetrove.data.dao.FavoriteFoodDao
import com.example.tastetrove.data.dao.HistoryDao
import com.example.tastetrove.data.model.FavoriteFoodModel
import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteFoodDataSource @Inject constructor(
    private val dao: FavoriteFoodDao
) {

    suspend fun getData(): Flow<Resource<List<FavoriteFoodModel>>> = flow {
        try {
            emit(Resource.Loading())
            val response = dao.getAll()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)


    suspend fun insertData(data: FavoriteFoodModel): Flow<Resource<FavoriteFoodModel>> = flow {
        try {
            emit(Resource.Loading())
            dao.insert(data)
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}