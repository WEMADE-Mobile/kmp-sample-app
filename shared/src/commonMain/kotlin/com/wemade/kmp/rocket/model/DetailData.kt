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