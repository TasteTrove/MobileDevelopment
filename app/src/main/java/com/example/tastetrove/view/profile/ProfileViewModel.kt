package com.example.tastetrove.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tastetrove.data.pref.UserModel
import com.example.tastetrove.data.repo.UserRepository
import com.example.tastetrove.data.response.UserResponse
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<UserResponse>()
    val user: MutableLiveData<UserResponse> = _user

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

}

