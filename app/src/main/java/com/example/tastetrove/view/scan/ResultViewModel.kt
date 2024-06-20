package com.example.tastetrove.view.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tastetrove.common.base.BaseViewModel
import com.example.tastetrove.data.model.FavoriteFoodModel
import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.data.model.api.ml.MLFoodResponse
import com.example.tastetrove.data.network.Resource
import com.example.tastetrove.data.repo.favoritefood.FavoriteFoodRepository
import com.example.tastetrove.data.repo.history.HistoryRepository
import com.example.tastetrove.data.repo.ml.MLRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val favoriteRepository: FavoriteFoodRepository,
    private val mlRepository: MLRepository
) : BaseViewModel() {

    private var _dbState = MutableLiveData<Resource<HistoryModel>>()
    var dbState: LiveData<Resource<HistoryModel>> = _dbState

    private var _dbFavState = MutableLiveData<Resource<FavoriteFoodModel>>()
    var dbFavState: LiveData<Resource<FavoriteFoodModel>> = _dbFavState

    private var _analyzeState = MutableLiveData<Resource<MLFoodResponse>>()
    var analyzeState: LiveData<Resource<MLFoodResponse>> = _analyzeState

    fun analyze(
        imageFile: File,
    ) {
        viewModelScope.launch {
            mlRepository.analyze(imageFile)
                .onEach {
                    _analyzeState.postValue(it)
                }
                .launchIn(viewModelScope)
        }
    }

    fun insertData(data: HistoryModel) {
        viewModelScope.launch {
            historyRepository.insertHistory(data)
                .onEach {
                    _dbState.postValue(it)
                }
                .launchIn(viewModelScope)
        }
    }


    fun insertFav(data: FavoriteFoodModel) {
        viewModelScope.launch {
            favoriteRepository.insertData(data)
                .onEach {
                    _dbFavState.postValue(it)
                }
                .launchIn(viewModelScope)
        }
    }

}