package com.example.tastetrove.data.repo.news

import com.example.tastetrove.data.model.api.news.ArticleResponse
import com.example.tastetrove.data.retrofit.NewsApiService
import com.example.tastetrove.data.network.Resource
import com.example.tastetrove.util.NewsUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsDataSource @Inject constructor(
    private val apiService: NewsApiService,
) {

    suspend fun getTopHeadline(q: String, category: String): Flow<Resource<ArticleResponse>> = flow {
        try {
            emit(Resource.Loading())
            val request = apiService.getTopHeadline(
                apiKey = NewsUrl.API_KEY,
                q = q,
                sources = null,
                category = category,
                country = null,
                pageSize = null,
                page = null
            )
            val response = request.body()
            if (request.isSuccessful) {
                emit(Resource.Success(response ?: ArticleResponse()))
            } else {
                emit(Resource.Error(request.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getEverything(q: String): Flow<Resource<ArticleResponse>> = flow {
        try {
            emit(Resource.Loading())
            val request = apiService.getEverything(
                apiKey = NewsUrl.API_KEY,
                q = q,
                sources = null,
                pageSize = null,
                page = null,
                from = null,
                to = null,
                qInTitle = null,
                domains = null,
                excludeDomains = null,
                language = null,
                sortBy = null,
            )


            val response = request.body()
            if (request.isSuccessful) {
                emit(Resource.Success(response ?: ArticleResponse()))
            } else {
                emit(Resource.Error(request.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}