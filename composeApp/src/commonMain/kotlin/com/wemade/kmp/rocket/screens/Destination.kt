package com.wemade.kmp.rocket.screens

import kotlinx.serialization.Serializable

@Serializable
object ListDestination

@Serializable
data class DetailDestination(
    val id: String,
    val rocket: String,
    val imageUrl: String,
    val title: String,
    val isSuccessLaunched: Boolean,
    val launchDate: String
)