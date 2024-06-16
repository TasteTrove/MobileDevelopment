package com.example.tastetrove.data.model.api.github


import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class SearchUsersApiModel(
    @SerializedName("total_count")
    var totalCount: Int? = 0,
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean? = false,
    @SerializedName("items")
    var items: List<UserApiModel>? = listOf()
) : Parcelable