package com.example.tastetrove.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.tastetrove.data.pref.UserModel
import com.example.tastetrove.data.pref.UserPreference
import com.example.tastetrove.data.response.ErrorResponse
import com.example.tastetrove.data.response.ListStoryItem
import com.example.tastetrove.data.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import com.example.tastetrove.data.response.Result
import com.example.tastetrove.data.response.auth.LoginResponse
import com.example.tastetrove.data.response.auth.RegisterResponse
import retrofit2.HttpException

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService

) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }


    fun signup(
        name: String, email: String, pass: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(name, email, pass)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            Log.d("register", e.message.toString())
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun login(
        email: String,
        pass: String
    ): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, pass)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            Log.d("login", e.message.toString())
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun getFoods(): List<ListStoryItem>? {
        val response = apiService.getFood()
        return if (!response.error!!) response.story else null
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(userPreference: UserPreference, apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }
}