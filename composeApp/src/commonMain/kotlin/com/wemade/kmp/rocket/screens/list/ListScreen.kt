package com.wemade.kmp.rocket.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wemade.kmp.rocket.model.ListData
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListScreen(
    listViewModel: ListViewModel = koinViewModel<ListViewModel>(),
    onItemClick: (ListData) -> Unit,
) {
    val dataList = listViewModel.launchList.collectAsStateWithLifecycle().value

    SharedTransitionLayout {
        AnimatedVisibility(visible = true) {
            ListView(
                dataList = dataList,
                onItemClick = onItemClick,
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this
            )
        }
    }
}