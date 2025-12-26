package com.wemade.kmp.rocket.viewmodel

import com.wemade.kmp.rocket.mapper.Launch
import com.wemade.kmp.rocket.mapper.toListItemUiModel
import com.wemade.kmp.rocket.model.ui.LaunchListItemUiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RocketListViewModel(dispatcher: CoroutineDispatcher) {
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)
    private val _state = MutableStateFlow(RocketListState())
    val state: StateFlow<RocketListState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<RocketListEffect>()
    val effect: SharedFlow<RocketListEffect> = _effect.asSharedFlow()

    fun onHandleEvent(event: RocketListEvent) {
        when (event) {
            RocketListEvent.LoadList -> {
                if (state.value.items.isEmpty()) {
                    loadList()
                }
            }

            is RocketListEvent.ItemClicked -> {
                scope.launch {
                    _effect.emit(RocketListEffect.NavigateToDetail(event.id))
                }
            }
        }
    }

    fun loadList() {
        scope.launch {
            _state.update {
                it.copy(isLoading = true, errorMessage = null)
            }
            runCatching {
                //todo UseCase호출
                listOf(
                    Launch(
                        id = "",
                        name = "",
                        dateUtc = "",
                        success = true,
                        patchSmall = "",
                        launchDate = ""
                    )
                )
            }.onSuccess { launches ->
                val uiModels =
                    launches.sortedByDescending { it.launchDate }.map { it.toListItemUiModel() }

                _state.update {
                    it.copy(
                        isLoading = false,
                        items = uiModels,
                        errorMessage = null
                    )
                }
            }.onFailure { throwable ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Unknown Error"
                    )
                }
            }
        }
    }

    private fun setLoading(isInitial: Boolean, loading: Boolean) {
        _state.update {
            if (isInitial) it.copy(isLoading = loading, errorMessage = null)
            else it.copy(errorMessage = null)
        }
    }

    fun clear() {
        scope.cancel()
    }

}

data class RocketListState(
    val isLoading: Boolean = false,
    val items: List<LaunchListItemUiModel> = emptyList(),
    val errorMessage: String? = null,
)

sealed interface RocketListEvent {
    object LoadList : RocketListEvent
    data class ItemClicked(val id: String) : RocketListEvent
}

sealed interface RocketListEffect {
    data class NavigateToDetail(val id: String) : RocketListEffect
}