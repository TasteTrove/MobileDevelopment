package com.example.tastetrove.view.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tastetrove.common.base.BaseViewModel
import com.example.tastetrove.data.model.FavoriteFoodModel
import com.example.tastetrove.data.network.Resource
import com.example.tastetrove.data.repo.favoritefood.FavoriteFoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteFoodRepository
) : BaseViewModel() {

    private var _dbState = MutableLiveData<Resource<FavoriteFoodModel>>()
    var dbState: LiveData<Resource<FavoriteFoodModel>> = _dbState

    private var _getDataState = MutableLiveData<Resource<List<FavoriteFoodModel>>>()
    var getDataState: LiveData<Resource<List<FavoriteFoodModel>>> = _getDataState

    fun insertData(data: FavoriteFoodModel) {
        viewModelScope.launch {
            repository.insertData(data)
                .onEach {
                    _dbState.postValue(it)
                }
                .launchIn(viewModelScope)
        }
    }

    fun getData() {
        viewModelScope.launch {
            repository.getData()
                .onEach {
                    _getDataState.postValue(it)
                }.launchIn(viewModelScope)
        }
    }

}