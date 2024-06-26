package com.example.tastetrove.view.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tastetrove.data.repo.UserRepository
import com.example.tastetrove.data.response.Result
import com.example.tastetrove.data.response.auth.RegisterResponse

class SignupViewModel (private val repository: UserRepository) : ViewModel() {
    fun register(name: String, email: String, pass: String): LiveData<Result<RegisterResponse>> =
        repository.signup(name, email, pass)
}