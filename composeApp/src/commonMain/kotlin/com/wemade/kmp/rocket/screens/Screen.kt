package com.wemade.kmp.rocket.screens

enum class Screen(val route: String) {
    List("list"),
    Detail("detail/{rocketTitle}")
}