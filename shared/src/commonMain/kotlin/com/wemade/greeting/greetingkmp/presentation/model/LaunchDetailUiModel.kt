package com.wemade.greeting.greetingkmp.presentation.model

data class LaunchDetailUiModel(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val statusText: String,
    val dateUtcRaw: String,
    val details: String?,
    val articleUrl: String?,
    val wikipediaUrl: String?,
    val webcastUrl: String?
)