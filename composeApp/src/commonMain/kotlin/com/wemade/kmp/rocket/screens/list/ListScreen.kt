package com.wemade.kmp.rocket.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import com.wemade.kmp.rocket.model.ListData
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListScreen(
    listViewModel: ListViewModel = koinViewModel<ListViewModel>(),
    onItemClick: (ListData) -> Unit,
) {
    // TODO: 삭제 더미 데이터
    val dataList = listViewModel.dummyLists

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