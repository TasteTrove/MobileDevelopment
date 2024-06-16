package com.example.tastetrove.data.repo.news

import com.example.tastetrove.data.model.api.news.Article
import com.example.tastetrove.data.network.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTopHeadline(q: String, category: String): Flow<Resource<List<Article>>>

    suspend fun getEverything(q: String): Flow<Resource<List<Article>>>

}