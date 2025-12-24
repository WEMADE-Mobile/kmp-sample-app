package com.wemade.kmp.rocket.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunchData(
    @SerialName("id")
    val id: String,
    @SerialName("links")
    val link: RocketLaunchLinks,
    @SerialName("name")
    val name: String,
    @SerialName("date_utc")
    val launchDateUTC: String,
    @SerialName("success")
    val launchSuccess: Boolean?,
    @SerialName("rocket")
    val rocketId: String
)

@Serializable
data class RocketLaunchLinks(
    @SerialName("patch")
    val patch: RocketLaunchLinkPatch?
)

@Serializable
data class RocketLaunchLinkPatch(
    @SerialName("small")
    val small: String?
)