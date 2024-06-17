package com.example.tastetrove.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tastetrove.data.model.FavoriteFoodModel


@Dao
interface FavoriteFoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: FavoriteFoodModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(articles: List<FavoriteFoodModel>)

    @Update
    fun update(data: FavoriteFoodModel)

    @Delete
    fun delete(data: FavoriteFoodModel)

    @Query("SELECT * FROM favorite_food ORDER BY table_id ASC")
    fun getAll(): List<FavoriteFoodModel>

    @Query("DELETE FROM favorite_food")
    fun nuke()

    @Query("SELECT * FROM favorite_food WHERE login LIKE '%' || :search || '%' ORDER BY table_id DESC")
    fun getByUserName(search: String): LiveData<FavoriteFoodModel>

}