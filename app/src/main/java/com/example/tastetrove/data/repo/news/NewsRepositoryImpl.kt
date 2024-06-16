package com.example.tastetrove.data.repo.news

import com.example.tastetrove.data.model.api.news.Article
import com.example.tastetrove.data.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val dataSource: NewsDataSource,
) : NewsRepository {

    override suspend fun getTopHeadline(q: String, category: String): Flow<Resource<List<Article>>> {
        return dataSource.getTopHeadline(q, category).map {
            return@map when (it) {
                is Resource.Success -> Resource.Success(it.data?.articles ?: listOf())
                is Resource.Error -> Resource.Error(it.message.toString())
                is Resource.Loading -> Resource.Loading()
            }
        }
    }

    override suspend fun getEverything(q: String): Flow<Resource<List<Article>>> {
        return dataSource.getEverything(q).map {
            return@map when (it) {
                is Resource.Success -> Resource.Success(it.data?.articles ?: listOf())
                is Resource.Error -> Resource.Error(it.message.toString())
                is Resource.Loading -> Resource.Loading()
            }
        }
    }


}