package com.example.tastetrove.data.repo.history

import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.data.network.Resource
import kotlinx.coroutines.flow.Flow


interface HistoryRepository {

    suspend fun getHistory(): Flow<Resource<List<HistoryModel>>>

    suspend fun insertHistory(data: HistoryModel): Flow<Resource<HistoryModel>>

}