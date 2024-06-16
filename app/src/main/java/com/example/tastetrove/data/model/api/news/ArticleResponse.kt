package com.example.tastetrove.data.model.api.news

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("totalResults")
    var totalResults: Int? = null,

    @SerializedName("code")
    var code: String? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("articles")
    var articles: List<Article>? = null

)