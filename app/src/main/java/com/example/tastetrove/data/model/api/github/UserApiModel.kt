package com.example.tastetrove.data.model.api.github


import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity(tableName = "user_data")
@Parcelize
@Keep
data class UserApiModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "table_id")
    var table_id: Int = 0,

    @ColumnInfo("login")
    @SerializedName("login")
    var login: String? = "",

    @ColumnInfo("id")
    @SerializedName("id")
    var id: Int? = 0,

    @ColumnInfo("node_id")
    @SerializedName("node_id")
    var nodeId: String? = "",

    @ColumnInfo("avatar_url")
    @SerializedName("avatar_url")
    var avatarUrl: String? = "",

    @ColumnInfo("gravatar_id")
    @SerializedName("gravatar_id")
    var gravatarId: String? = "",

    @ColumnInfo("url")
    @SerializedName("url")
    var url: String? = "",

    @ColumnInfo("html_url")
    @SerializedName("html_url")
    var htmlUrl: String? = "",

    @ColumnInfo("followers_url")
    @SerializedName("followers_url")
    var followersUrl: String? = "",

    @ColumnInfo("following_url")
    @SerializedName("following_url")
    var followingUrl: String? = "",

    @ColumnInfo("gists_url")
    @SerializedName("gists_url")
    var gistsUrl: String? = "",

    @ColumnInfo("starred_url")
    @SerializedName("starred_url")
    var starredUrl: String? = "",

    @ColumnInfo("subscriptions_url")
    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = "",

    @ColumnInfo("organizations_url")
    @SerializedName("organizations_url")
    var organizationsUrl: String? = "",

    @ColumnInfo("repos_url")
    @SerializedName("repos_url")
    var reposUrl: String? = "",

    @ColumnInfo("events_url")
    @SerializedName("events_url")
    var eventsUrl: String? = "",

    @ColumnInfo("received_events_url")
    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = "",

    @ColumnInfo("type")
    @SerializedName("type")
    var type: String? = "",

    @ColumnInfo("site_admin")
    @SerializedName("site_admin")
    var siteAdmin: Boolean? = false,

    @ColumnInfo("name")
    @SerializedName("name")
    var name: String? = "",

    @ColumnInfo("compString")
    @SerializedName("compString")
    var compString: String? = "",

    @ColumnInfo("blog")
    @SerializedName("blog")
    var blog: String? = "",

    @ColumnInfo("location")
    @SerializedName("location")
    var location: String? = "",

    @ColumnInfo("email")
    @SerializedName("email")
    var email: String? = "",

    @ColumnInfo("hireable")
    @SerializedName("hireable")
    var hireable: Boolean? = false,

    @ColumnInfo("bio")
    @SerializedName("bio")
    var bio: String? = "",

    @ColumnInfo("twitter_username")
    @SerializedName("twitter_username")
    var twitterUsername: String? = "",

    @ColumnInfo("public_repos")
    @SerializedName("public_repos")
    var publicRepos: Int? = 0,

    @ColumnInfo("public_gists")
    @SerializedName("public_gists")
    var publicGists: Int? = 0,

    @ColumnInfo("followers")
    @SerializedName("followers")
    var followers: Int? = 0,

    @ColumnInfo("following")
    @SerializedName("following")
    var following: Int? = 0,

    @ColumnInfo("score")
    @SerializedName("score")
    var score: Double? = 0.0,

    @ColumnInfo("created_at")
    @SerializedName("created_at")
    var createdAt: String? = "",

    @ColumnInfo("updated_at")
    @SerializedName("updated_at")
    var updatedAt: String? = "",
) : Parcelable