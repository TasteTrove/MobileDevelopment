package com.example.tastetrove.data.repo.favoritefood

import com.example.tastetrove.data.model.FavoriteFoodModel
import com.example.tastetrove.data.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteFoodRepositoryImpl @Inject constructor(
    private val dataSource: FavoriteFoodDataSource
) : FavoriteFoodRepository {

    override suspend fun getData(): Flow<Resource<List<FavoriteFoodModel>>> {
        return dataSource.getData().map {
            return@map when (it) {
                is Resource.Success -> Resource.Success(it.data ?: listOf())
                is Resource.Error -> Resource.Error(it.message.toString())
                is Resource.Loading -> Resource.Loading()
            }
        }
    }

    override suspend fun insertData(data: FavoriteFoodModel): Flow<Resource<FavoriteFoodModel>> {
        return dataSource.insertData(data).map {
            return@map when (it) {
                is Resource.Success -> Resource.Success(it.data ?: FavoriteFoodModel())
                is Resource.Error -> Resource.Error(it.message.toString())
                is Resource.Loading -> Resource.Loading()
            }
        }
    }

}