package com.example.tastetrove.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tastetrove.data.pref.UserModel
import com.example.tastetrove.data.repo.UserRepository
import com.example.tastetrove.data.response.ListStoryItem
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    private val _foods = MutableLiveData<List<ListStoryItem>>()
    val foods: LiveData<List<ListStoryItem>> = _foods

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getStories() {
        viewModelScope.launch {
            try {
                val foods = repository.getFoods()
                Log.d("MainViewModel", "Fetched stories: $foods")
                _foods.value = foods ?: emptyList()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching stories: ${e.message}", e)
                _foods.value = emptyList()
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}