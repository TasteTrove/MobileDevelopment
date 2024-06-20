package com.example.tastetrove.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tastetrove.data.pref.UserModel
import com.example.tastetrove.data.repo.UserRepository
import com.example.tastetrove.data.response.FoodGambarsItem
import com.example.tastetrove.data.response.FoodsResponseItem
import com.example.tastetrove.data.response.Result
import com.example.tastetrove.data.response.auth.RegisterResponse
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getFoods(): LiveData<Result<List<FoodsResponseItem>>> =
        repository.getFoods()

}