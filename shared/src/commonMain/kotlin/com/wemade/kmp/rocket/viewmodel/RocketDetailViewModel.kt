package com.wemade.kmp.rocket.viewmodel

import com.wemade.kmp.rocket.mapper.LaunchDetail
import com.wemade.kmp.rocket.mapper.toDetailUiModel
import com.wemade.kmp.rocket.model.ui.LaunchDetailUiModel
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

class RocketDetailViewModel(
    private val launchId: String,
    private val getLaunchDetailUseCase: GetLaunchDetailUseCase,
    private val dispatcher: CoroutineDispatcher
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

    private val _state = MutableStateFlow(RocketDetailState())
    val state: StateFlow<RocketDetailState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<RocketDetailEffect>()
    val effect: SharedFlow<RocketDetailEffect> = _effect.asSharedFlow()

    fun dispatch(event: RocketDetailEvent) {
        when (event) {
            RocketDetailEvent.Load -> load()
        }
    }

    private fun load() {
        scope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            runCatching { getLaunchDetailUseCase(launchId) }
                .onSuccess { launch: LaunchDetail ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            detail = launch.toDetailUiModel(),
                            errorMessage = null
                        )
                    }
                }
                .onFailure { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "unknown error"
                        )
                    }
                }
        }
    }

    fun clear() {
        scope.cancel()
    }
}

fun interface GetLaunchDetailUseCase {
    suspend operator fun invoke(id: String): LaunchDetail
}

data class RocketDetailState(
    val isLoading: Boolean = false,
    val detail: LaunchDetailUiModel? = null,
    val errorMessage: String? = null
)

sealed interface RocketDetailEvent {
    object Load : RocketDetailEvent
}

sealed interface RocketDetailEffect {
    data class OpenExternalUrl(val url: String) : RocketDetailEffect
}
