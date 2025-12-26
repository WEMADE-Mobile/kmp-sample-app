package com.wemade.kmp.rocket

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.wemade.kmp.rocket.screens.DetailDestination
import com.wemade.kmp.rocket.screens.ListDestination
import com.wemade.kmp.rocket.screens.detail.DetailScreen
import com.wemade.kmp.rocket.screens.list.ListScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface {
            val navController = rememberNavController()

            SharedTransitionLayout {
                NavHost(
                    navController = navController,
                    startDestination = ListDestination
                ) {
                    // 1. 리스트 화면
                    composable<ListDestination> {
                        AnimatedVisibility(visible = true) {
                            ListScreen(onItemClick = { item ->
                                navController.navigate(DetailDestination(id = item.id, rocket = item.rocket))
                            })
                        }
                    }

                    // 2. 상세 화면
                    composable<DetailDestination> { backStackEntry ->
                        val detail: DetailDestination = backStackEntry.toRoute()
                        AnimatedVisibility(visible = true) {
                            DetailScreen(
                                launchId = detail.id,
                                onBack = {
                                    navController.popBackStack()
                                },
                                sharedTransitionScope = this@SharedTransitionLayout,
                                animatedVisibilityScope = this
                            )
                        }
                    }
                }
            }
        }
    }
}
