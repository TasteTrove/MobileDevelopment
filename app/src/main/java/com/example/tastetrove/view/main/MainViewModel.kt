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

    private val _stories = MutableLiveData<List<ListStoryItem>>()
    val stories: LiveData<List<ListStoryItem>> = _stories

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getFoods() {
        viewModelScope.launch {
            try {
                val stories = repository.getFoods()
                Log.d("MainViewModel", "Fetched stories: $stories")
                _stories.value = stories ?: emptyList()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching stories: ${e.message}", e)
                _stories.value = emptyList()
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}