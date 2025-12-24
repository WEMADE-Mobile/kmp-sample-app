package com.wemade.kmp.rocket.repository

import com.wemade.kmp.rocket.repository.model.RocketLaunchData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RocketComponent {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getLaunchList(): List<RocketLaunchData> {
        return try {
            httpClient.get("https://api.spacexdata.com/v5/launches").body()
        } catch (e: Exception) {
            emptyList()
        }
    }
}