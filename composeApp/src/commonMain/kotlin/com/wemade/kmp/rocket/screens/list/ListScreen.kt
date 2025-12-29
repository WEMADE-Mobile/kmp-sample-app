package com.wemade.kmp.rocket.screens.list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wemade.kmp.rocket.model.ListData
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListScreen(
    listViewModel: RocketListViewModel = koinViewModel<RocketListViewModel>(),
    navigateToDetail: (ListData) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val dataList = listViewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(listViewModel) {
        listViewModel.effect.collect { effect ->
            when (effect) {
                is RocketListEffect.NavigateToDetail -> navigateToDetail(effect.item)
            }
        }
    }

    ListView(
        dataList = dataList.items,
        onItemClick = { data ->
            listViewModel.onHandleEvent(RocketListEvent.ItemClicked(data))
        },
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedVisibilityScope
    )
}