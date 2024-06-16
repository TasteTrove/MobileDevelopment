package com.example.tastetrove.data.repo.history

import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.data.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val dataSource: HistoryDataSource
) : HistoryRepository {

    override suspend fun getHistory(): Flow<Resource<List<HistoryModel>>> {
        return dataSource.getHistory().map {
            return@map when (it) {
                is Resource.Success -> Resource.Success(it.data ?: listOf())
                is Resource.Error -> Resource.Error(it.message.toString())
                is Resource.Loading -> Resource.Loading()
            }
        }
    }

    override suspend fun insertHistory(data: HistoryModel): Flow<Resource<HistoryModel>> {
        return dataSource.insertHistory(data).map {
            return@map when (it) {
                is Resource.Success -> Resource.Success(it.data ?: HistoryModel())
                is Resource.Error -> Resource.Error(it.message.toString())
                is Resource.Loading -> Resource.Loading()
            }
        }
    }

}