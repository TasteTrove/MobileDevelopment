package com.example.tastetrove.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tastetrove.data.dao.HistoryDao
import com.example.tastetrove.data.model.HistoryModel
import com.yalantis.ucrop.BuildConfig

@Database(entities = [(HistoryModel::class)], version = 1)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {

        fun getInstance(context: Context): HistoryDatabase {
            return buildDatabase(context)
        }

        private fun buildDatabase(context: Context): HistoryDatabase {
            return if (BuildConfig.DEBUG) {
                Room.databaseBuilder(context, HistoryDatabase::class.java, "history_db")
                    .fallbackToDestructiveMigration() // FOR DEVELOPMENT ONLY !!!!
                    .build()
            } else {
                Room.databaseBuilder(context, HistoryDatabase::class.java, "history_db")
                    .build()
            }
        }

    }
}