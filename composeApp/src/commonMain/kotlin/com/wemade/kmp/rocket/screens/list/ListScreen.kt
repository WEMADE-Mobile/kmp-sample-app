package com.wemade.kmp.rocket.screens.list

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wemade.kmp.rocket.model.dummyDetailData
import com.wemade.kmp.rocket.screens.Screen
import com.wemade.kmp.rocket.screens.detail.DetailScreen
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListScreen(
    listViewModel: ListViewModel = koinViewModel<ListViewModel>()
) {
    val navController = rememberNavController()
    val dataList = listViewModel.dummyLists
    val dataDetail = listViewModel.dummyDetail

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = Screen.List.route
        ) {
            // 1. 리스트 화면
            composable(Screen.List.route) {
                ListView(
                    dataList = dataList,
                    onItemClick = { item ->
                        navController.navigate("detail/${item.title}")
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this
                )
            }

            // 2. 상세 화면
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("rocketTitle") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                // [수정됨] arguments 대신 savedStateHandle 사용 (가장 안전함)
                val rocketTitle = backStackEntry.savedStateHandle.get<String>("rocketTitle")

                // 데이터 찾기
                val selectedItem = dataList.find { it.title == rocketTitle }

                if (selectedItem != null) {
                    DetailScreen(
                        itemData = dummyDetailData,
                        navController = navController,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this
                    )
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("데이터를 찾을 수 없습니다: $rocketTitle")
                    }
                }
            }
        }
    }
}