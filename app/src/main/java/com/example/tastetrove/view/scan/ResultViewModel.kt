package com.example.tastetrove.view.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tastetrove.common.base.BaseViewModel
import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.data.network.Resource
import com.example.tastetrove.data.repo.history.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val repository: HistoryRepository,
) : BaseViewModel() {

    private var _dbState = MutableLiveData<Resource<HistoryModel>>()
    var dbState: LiveData<Resource<HistoryModel>> = _dbState

    fun insertData(data: HistoryModel) {
        viewModelScope.launch {
            repository.insertHistory(data)
                .onEach {
                    _dbState.postValue(it)
                }
                .launchIn(viewModelScope)
        }
    }

}