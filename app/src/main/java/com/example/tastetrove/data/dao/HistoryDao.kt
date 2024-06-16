package com.example.tastetrove.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tastetrove.data.model.HistoryModel


@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: HistoryModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(articles: List<HistoryModel>)

    @Update
    fun update(data: HistoryModel)

    @Delete
    fun delete(data: HistoryModel)

    @Query("SELECT * FROM user_data ORDER BY table_id ASC")
    fun getAll(): List<HistoryModel>

    @Query("DELETE FROM user_data")
    fun nuke()

    @Query("SELECT * FROM user_data WHERE login LIKE '%' || :search || '%' ORDER BY table_id DESC")
    fun getByUserName(search: String): LiveData<HistoryModel>

}