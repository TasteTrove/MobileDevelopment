package com.example.tastetrove.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "history_scan")
@Parcelize
@Keep
data class HistoryModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "table_id")
    var table_id: Int = 0,

    @SerializedName("deskripsi")
    @ColumnInfo("deskripsi")
    var deskripsi: String? = "",

    @SerializedName("lokasi")
    @ColumnInfo("lokasi")
    var lokasi: String? = "",

    @SerializedName("nama")
    @ColumnInfo("nama")
    var nama: String? = "",

    @SerializedName("image")
    @ColumnInfo("image")
    var image: String? = "",

    ) : Parcelable {

    fun toFavoriteModel(): FavoriteFoodModel {
        return FavoriteFoodModel(
            table_id, deskripsi, lokasi, nama, image
        )
    }

}