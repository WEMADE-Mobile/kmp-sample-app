package com.wemade.kmp.rocket.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wemade.kmp.rocket.RocketRepository
import com.wemade.kmp.rocket.model.ListData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RocketListViewModel(private val repository: RocketRepository) : ViewModel() {
    private val _state = MutableStateFlow(RocketListState())
    val state: StateFlow<RocketListState> = _state.asStateFlow()

    init {
        onHandleEvent(RocketListEvent.LoadList)
    }

    private fun onHandleEvent(event: RocketListEvent) {
        when (event) {
            RocketListEvent.LoadList -> {
                if (state.value.items.isEmpty()) {
                    loadList()
                }
            }
        }
    }

    private fun loadList() {
        viewModelScope.launch {
            repository.getLaunchList()
                .onStart {
                    _state.update { it.copy(isLoading = true, errorMessage = null) }
                }
                .catch { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "Unknown Error"
                        )
                    }
                }
                .collect { launches ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            items = launches,
                            errorMessage = null
                        )
                    }
                }
        }
    }
}

data class RocketListState(
    val isLoading: Boolean = false,
    val items: List<ListData> = emptyList(),
    val errorMessage: String? = null,
)

sealed interface RocketListEvent {
    object LoadList : RocketListEvent
}
