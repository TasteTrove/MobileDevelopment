package com.example.tastetrove.data.model.api.news

import com.google.gson.annotations.SerializedName

data class SourceResponse(

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("sources")
    var sources: List<Source>? = null

)