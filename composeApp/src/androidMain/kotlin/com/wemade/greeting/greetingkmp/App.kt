package com.wemade.greeting.greetingkmp

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wemade.greeting.greetingkmp.navigation.Screen
import com.wemade.greeting.greetingkmp.view.RocketDetailView
import com.wemade.greeting.greetingkmp.view.RocketListView
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview
fun App(
    mainViewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()
    val dataList = mainViewModel.dummyData

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = Screen.List.route
        ) {
            // 1. 리스트 화면
            composable(Screen.List.route) {
                RocketListView(
                    dataList = dataList,
                    onItemClick = { item ->
                        navController.navigate("detail/${item.title}")
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this
                )
            }

            // 2. 상세 화면
            composable(Screen.Detail.route) { backStackEntry ->
                val rocketTitle = backStackEntry.arguments?.getString("rocketTitle")
                val selectedItem = dataList.find { it.title == rocketTitle }

                if (selectedItem != null) {
                    RocketDetailView(
                        itemData = selectedItem,
                        navController = navController,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this
                    )
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("데이터를 찾을 수 없습니다.")
                    }
                }
            }
        }
    }
}