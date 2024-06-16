package com.example.tastetrove.data.repo.history

import com.example.tastetrove.data.dao.HistoryDao
import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryDataSource @Inject constructor(
    private val dao: HistoryDao
) {

    suspend fun getHistory(): Flow<Resource<List<HistoryModel>>> = flow {
        try {
            emit(Resource.Loading())
            val response = dao.getAll()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)


    suspend fun insertHistory(data: HistoryModel): Flow<Resource<HistoryModel>> = flow {
        try {
            emit(Resource.Loading())
            dao.insert(data)
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}