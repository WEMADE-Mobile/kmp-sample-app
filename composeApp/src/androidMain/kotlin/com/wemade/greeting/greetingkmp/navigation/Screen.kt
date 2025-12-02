package com.wemade.greeting.greetingkmp.navigation

enum class Screen(val route: String) {
    List("list"),
    Detail("detail/{rocketTitle}") // Title을 ID처럼 사용하여 데이터 전달
}