package com.wemade.kmp.rocket.model

import kotlin.random.Random

data class ListData(
    val title: String,
    val description: String,
    val createdAt: String,
    val imageUrl: String,
    val isSuccessLaunched: Boolean
)

// TODO: 삭제 - 더미 데이터
val dummyData = List(20) { index ->
    ListData(
        title = "Rocket ${index + 1}",
        description = "Description for Rocket ${index + 1}",
        createdAt = "2025-12-01 15:11:00",
        imageUrl = "https://picsum.photos/id/${index + 1}/100/140",
        isSuccessLaunched = Random.nextBoolean()
    )
}