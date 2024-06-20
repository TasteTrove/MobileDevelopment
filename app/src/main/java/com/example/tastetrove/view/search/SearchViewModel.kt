package com.example.tastetrove.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tastetrove.data.repo.UserRepository
import com.example.tastetrove.data.response.FoodsResponseItem
import com.example.tastetrove.data.response.Result


class SearchViewModel(private val repository: UserRepository) : ViewModel() {

    fun getFoods(): LiveData<Result<List<FoodsResponseItem>>> =
        repository.getFoods()

}