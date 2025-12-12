package com.wemade.greeting.greetingkmp.presentation.model

data class LaunchListItemUiModel(
    val id: String,
    val imageUrl: String?,
    val statusText: String,
    val name: String,
    val launchDate: String
)