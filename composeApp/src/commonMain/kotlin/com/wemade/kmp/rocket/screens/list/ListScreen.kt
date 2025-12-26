package com.wemade.kmp.rocket.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wemade.kmp.rocket.model.ListData
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListScreen(
    listViewModel: RocketListViewModel = koinViewModel<RocketListViewModel>(),
    onItemClick: (ListData) -> Unit,
) {
    val dataList = listViewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(listViewModel) {
        listViewModel.effect.collect { effect ->
            when (effect) {
                is RocketListEffect.NavigateToDetail -> onItemClick(effect.item)
            }
        }
    }

    SharedTransitionLayout {
        AnimatedVisibility(visible = true) {
            ListView(
                dataList = dataList.items,
                onItemClick = onItemClick,
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this
            )
        }
    }
}