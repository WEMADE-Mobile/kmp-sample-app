package com.wemade.kmp.rocket.model

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class DetailData(
    val id: String,
    val rocket: String,
    val title: String,
    val description: String,
    val createdAt: String,
    val imageUrl: String,
    val isSuccessLaunched: Boolean,
    val height: Double, // 높이
    val diameter: Double,   // 지름
    val mass: Double,   // 무게
    val images: List<String>,
    val wikipedia: String
)

//// TODO: 삭제 - 더미 데이터
//val dummyDetailData = DetailData(
//    id = "id_0",
//    rocket = "test_0",
//    title = "Rocket 1",
//    description = "Description for Rocket",
//    createdAt = "2025-12-01 15:11:00",
//    imageUrl = "https://picsum.photos/id/1/100/140",
//    isSuccessLaunched = Random.nextBoolean(),
//    height = 123.0,
//    diameter = 456.0,
//    mass = 789.0,
//    images = List(size = 3) { index ->
//        "https://picsum.photos/id/${index + 1}/100/140"
//    },
//    wikipedia = "Wiki"
//)