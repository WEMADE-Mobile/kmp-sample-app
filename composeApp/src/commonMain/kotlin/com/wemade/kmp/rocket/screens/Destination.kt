package com.wemade.kmp.rocket.screens

import kotlinx.serialization.Serializable

@Serializable
object ListDestination

@Serializable
data class DetailDestination(val id: String, val rocket: String)