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
                            ListScreen(
                                onItemClick = { data ->
                                    navController.navigate(
                                        DetailDestination(
                                            id = data.id,
                                            imageUrl = data.imageUrl ?: "",
                                            rocket = data.rocket,
                                            title = data.title,
                                            launchDate = data.launchDate,
                                            isSuccessLaunched = data.isSuccessLaunched
                                        )
                                    )
                                },
                                sharedTransitionScope = this@SharedTransitionLayout,
                                animatedVisibilityScope = this
                            )
                        }
                    }

                    // 2. 상세 화면
                    composable<DetailDestination> { backStackEntry ->
                        val detail: DetailDestination = backStackEntry.toRoute()
                        AnimatedVisibility(visible = true) {
                            DetailScreen(
                                launchId = detail.id,
                                rocket = detail.rocket,
                                imageUrl = detail.imageUrl,
                                title = detail.title,
                                launchDate = detail.launchDate,
                                isSuccessLaunched = detail.isSuccessLaunched,
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
