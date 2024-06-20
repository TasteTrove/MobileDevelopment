package com.example.tastetrove.view.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.tastetrove.data.pref.UserModel
import com.example.tastetrove.data.repo.UserRepository
import com.example.tastetrove.data.response.FoodsResponseItem
import com.example.tastetrove.data.response.Result
class MainViewModel(private val repository: UserRepository) : ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getFoods(): LiveData<Result<List<FoodsResponseItem>>> =
        repository.getFoods()

}