package com.wemade.kmp.rocket.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchData(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String, // 미션 이름

    @SerialName("date_utc")
    val dateUtc: String, // 발사 날짜 (ISO 8601)

    @SerialName("date_unix")
    val dateUnix: Long,

    @SerialName("rocket")
    val rocketId: String,

    @SerialName("success")
    val success: Boolean? = null,

    @SerialName("details")
    val details: String? = null,

    @SerialName("upcoming")
    val upcoming: Boolean,

    @SerialName("links")
    val links: Links? = null,

    @SerialName("failures")
    val failures: List<Failure> = emptyList()
)

// --- 하위 클래스 ---

@Serializable
data class Links(
    @SerialName("patch")
    val patch: Patch? = null,
    @SerialName("wikipedia")
    val wikipedia: String? = null,
    @SerialName("webcast")
    val webcast: String? = null,
    @SerialName("article")
    val article: String? = null
)

@Serializable
data class Patch(
    @SerialName("small")
    val small: String? = null, // 리스트나 상세 상단에 보여줄 미션 패치 이미지
    @SerialName("large")
    val large: String? = null
)

@Serializable
data class Failure(
    @SerialName("time")
    val time: Int? = null,
    @SerialName("altitude")
    val altitude: Int? = null,
    @SerialName("reason")
    val reason: String? = null
)