package com.wemade.kmp.rocket.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketData(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("type")
    val type: String,

    @SerialName("description")
    val description: String,

    @SerialName("first_flight")
    val firstFlight: String,

    @SerialName("active")
    val active: Boolean,

    @SerialName("height")
    val height: Distance,

    @SerialName("diameter")
    val diameter: Distance,

    @SerialName("mass")
    val mass: Mass,

    @SerialName("flickr_images")
    val flickrImages: List<String>,

    @SerialName("wikipedia")
    val wikipedia: String
)

@Serializable
data class Distance(
    @SerialName("meters")
    val meters: Double
)

@Serializable
data class Mass(
    @SerialName("kg")
    val kg: Double // JSON은 Int일 수 있지만, DetailData가 Double이므로 자동 형변환 혹은 안전하게 Double로 수신
)