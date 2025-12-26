package com.wemade.kmp.rocket.model

import kotlinx.serialization.Serializable

@Serializable
data class ListData(
    val id: String,
    val rocket: String,
    val title: String,
    val launchDate: String,
    val imageUrl: String?,
    val isSuccessLaunched: Boolean
)