package com.wemade.kmp.rocket.model.ui

data class LaunchListItemUiModel(
    val id: String,
    val imageUrl: String?,
    val statusText: String,
    val name: String,
    val launchDate: String
)