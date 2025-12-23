package com.wemade.kmp.rocket

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wemade.kmp.rocket.screens.list.ListScreen
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable
object ListDestination

@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = ListDestination
            ) {
                composable<ListDestination> {
                    ListScreen()
                }

                // TODO : detail 화면
            }
        }
    }
}

