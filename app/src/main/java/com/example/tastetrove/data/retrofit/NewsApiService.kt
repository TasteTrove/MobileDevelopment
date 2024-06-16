package com.example.tastetrove.data.retrofit

import com.example.tastetrove.data.model.api.news.ArticleResponse
import com.example.tastetrove.util.NewsConstant
import com.example.tastetrove.util.NewsUrl.URL_EVERYTHING
import com.example.tastetrove.util.NewsUrl.URL_TOP_HEADLINE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET(URL_TOP_HEADLINE)
    suspend fun getTopHeadline(
        @Query(NewsConstant.QUERY_API_KEY) apiKey: String,
        @Query(NewsConstant.QUERY_Q) q: String?,
        @Query(NewsConstant.QUERY_SOURCES) sources: String?,
        @Query(NewsConstant.QUERY_CATEGORY) category: String?,
        @Query(NewsConstant.QUERY_COUNTRY) country: String?,
        @Query(NewsConstant.QUERY_PAGE_SIZE) pageSize: Int?,
        @Query(NewsConstant.QUERY_PAGE) page: Int?,
    ): Response<ArticleResponse>

    // Get Everything
    @GET(URL_EVERYTHING)
    suspend fun getEverything(
        @Query(NewsConstant.QUERY_API_KEY) apiKey: String,
        @Query(NewsConstant.QUERY_Q) q: String?,
        @Query(NewsConstant.QUERY_FROM) from: String?,
        @Query(NewsConstant.QUERY_TO) to: String?,
        @Query(NewsConstant.QUERY_Q_IN_TITLE) qInTitle: String?,
        @Query(NewsConstant.QUERY_SOURCES) sources: String?,
        @Query(NewsConstant.QUERY_DOMAINS) domains: String?,
        @Query(NewsConstant.QUERY_EXCLUDE_DOMAINS) excludeDomains: String?,
        @Query(NewsConstant.QUERY_LANGUAGE) language: String?,
        @Query(NewsConstant.QUERY_SORT_BY) sortBy: String?,
        @Query(NewsConstant.QUERY_PAGE_SIZE) pageSize: Int?,
        @Query(NewsConstant.QUERY_PAGE) page: Int?,
    ): Response<ArticleResponse>

}