package com.wemade.kmp.rocket.model

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class ListData(
    val id: String,
    val rocket: String,
    val title: String,
    val description: String,
    val createdAt: String,
    val imageUrl: String,
    val isSuccessLaunched: Boolean
)

// TODO: 삭제 - 더미 데이터
val dummyData = List(20) { index ->
    ListData(
        id = "id_$index",
        rocket = "test_$index",
        title = "Rocket ${index + 1}",
        description = "Description for Rocket ${index + 1}",
        createdAt = "2025-12-01 15:11:00",
        imageUrl = "https://picsum.photos/id/${index + 1}/100/140",
        isSuccessLaunched = Random.nextBoolean()
    )
}