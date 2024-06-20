package com.example.tastetrove.data.model.api.ml


import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tastetrove.data.model.FavoriteFoodModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_ml_food")
@Parcelize
@Keep
data class MLFoodResponse(
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

    fun toFavoriteModel(images: String? ="") : FavoriteFoodModel{
        return FavoriteFoodModel(
            deskripsi = deskripsi,
            lokasi = lokasi,
            nama = nama,
            image = if (images ==""){
                image
            } else {
                images
            }
        )
    }
}