package com.example.tastetrove.view.scan.historyscan

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
class HistoryViewModel @Inject constructor(
    private val repository: HistoryRepository,
) : BaseViewModel() {

    private var _dbState = MutableLiveData<Resource<List<HistoryModel>>>()
    var dbState: LiveData<Resource<List<HistoryModel>>> = _dbState

    fun getHistory() {
        viewModelScope.launch {
            repository.getHistory()
                .onEach {
                    _dbState.postValue(it)
                }
                .launchIn(viewModelScope)
        }
    }

}