package com.wemade.kmp.rocket.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wemade.kmp.rocket.RocketRepository
import com.wemade.kmp.rocket.model.ListData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: RocketRepository
) : ViewModel() {
    private val _launchList = MutableStateFlow<List<ListData>>(listOf())
    val launchList: StateFlow<List<ListData>> get() = _launchList

    init {
        viewModelScope.launch {
            repository.getLaunchList().collect {
                _launchList.emit(it)
            }
        }
    }
}