package com.wemade.greeting.greetingkmp.model

import kotlin.random.Random

data class RocketItemData(
    val title: String,
    val description: String,
    val createdAt: String,
    val imageUrl: String,
    val isSuccessLaunched: Boolean
)

val dummyData = List(20) { index ->
    RocketItemData(
        title = "Rocket ${index + 1}",
        description = "Description for Rocket ${index + 1}",
        createdAt = "2025-12-01 15:11:00",
        imageUrl = "https://picsum.photos/id/${index + 1}/100/140",
        isSuccessLaunched = Random.nextBoolean()
    )
}