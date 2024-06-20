package com.example.tastetrove.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tastetrove.data.dao.FavoriteFoodDao
import com.example.tastetrove.data.dao.HistoryDao
import com.example.tastetrove.data.model.FavoriteFoodModel
import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.view.favorite.FavoriteActivity
import com.yalantis.ucrop.BuildConfig

@Database(
    entities = [
        HistoryModel::class,
        FavoriteFoodModel::class
    ], version = 3
)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    abstract fun favoritesFoodDao(): FavoriteFoodDao

    companion object {

        fun getInstance(context: Context): FoodDatabase {
            return buildDatabase(context)
        }

        private fun buildDatabase(context: Context): FoodDatabase {
            return if (BuildConfig.DEBUG) {
                Room.databaseBuilder(context, FoodDatabase::class.java, "food_db")
                    .fallbackToDestructiveMigration() // FOR DEVELOPMENT ONLY !!!!
                    .build()
            } else {
                Room.databaseBuilder(context, FoodDatabase::class.java, "food_db")
                    .build()
            }
        }

    }
}