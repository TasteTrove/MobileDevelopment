package com.example.tastetrove.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_data")
@Parcelize
@Keep
data class HistoryModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "table_id")
    var table_id: Int = 0,

    @ColumnInfo("login")
    var image: String? = "",

    @ColumnInfo("label")
    var label: String? = "",

    @ColumnInfo("score")
    var score: String? = ""

 ) : Parcelable