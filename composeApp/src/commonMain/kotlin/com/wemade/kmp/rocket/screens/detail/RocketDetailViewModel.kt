package com.wemade.kmp.rocket.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wemade.kmp.rocket.RocketRepository
import com.wemade.kmp.rocket.model.DetailData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RocketDetailViewModel(
    private val repository: RocketRepository,
    private val launchId: String,
) : ViewModel() {
    private val _state = MutableStateFlow(RocketDetailState())
    val state: StateFlow<RocketDetailState> = _state.asStateFlow()

    init {
        onHandleEvent(RocketDetailEvent.LoadDetail)
    }

    private fun onHandleEvent(event: RocketDetailEvent) {
        when (event) {
            RocketDetailEvent.LoadDetail -> loadDetail()
        }
    }

    private fun loadDetail() {
        viewModelScope.launch {
            repository.getRocketLaunchDetail(launchId).onStart {
                _state.update { it.copy(isLoading = true, errorMessage = null) }
            }.catch { throwable ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Unknown Error"
                    )
                }
            }.collect { detail ->
                _state.update { it.copy(isLoading = false, detail = detail, errorMessage = null) }

            }
        }
    }
}

data class RocketDetailState(
    val isLoading: Boolean = false,
    val detail: DetailData? = null,
    val errorMessage: String? = null,
)

sealed interface RocketDetailEvent {
    object LoadDetail : RocketDetailEvent
}
