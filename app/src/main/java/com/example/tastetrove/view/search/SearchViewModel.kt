package com.example.tastetrove.view.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tastetrove.data.response.FoodResponse
import com.example.tastetrove.data.response.ListStoryItem
import com.example.tastetrove.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    private val _listFood = MutableLiveData<List<ListStoryItem>>()
    val listFood: LiveData<List<ListStoryItem>> = _listFood

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "SearchActivity"
    }

    init {
        findFood("Rendang")
    }

    fun findFood(q: String){
        _isLoading.value = true
        val client = ApiConfig.apiService().getSearch(q)
        client.enqueue(object : Callback<FoodResponse> {
            override fun onResponse(
                call: Call<FoodResponse>,
                response: Response<FoodResponse>
            ){
                _isLoading.value = false
                if (response.isSuccessful){
                    _listFood.value = response.body()?.story
                }else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable){
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

}