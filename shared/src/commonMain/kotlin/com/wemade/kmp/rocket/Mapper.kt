package com.wemade.kmp.rocket

import com.wemade.kmp.rocket.model.ListData
import com.wemade.kmp.rocket.model.DetailData
import com.wemade.kmp.rocket.repository.model.LaunchData
import com.wemade.kmp.rocket.repository.model.RocketData
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime


fun LaunchData.toDomain(): ListData = ListData(
    id = id,
    rocket = rocketId,
    title = name,
    launchDate = dateUtc.toFormatted(),
    imageUrl = links?.patch?.small,
    isSuccessLaunched = success ?: false // null일 경우 실패로 처리
)

fun RocketData.toDetailData(): DetailData = DetailData(
    id = this.id,
    rocket = this.type,
    title = this.name,
    description = this.description,
    createdAt = this.firstFlight,
    imageUrl = this.flickrImages.firstOrNull() ?: "",
    isSuccessLaunched = this.active,
    height = this.height.meters,
    diameter = this.diameter.meters,
    mass = this.mass.kg.toDouble(),
    images = this.flickrImages,
    wikipedia = this.wikipedia
)

fun mapToDetailData(launch: LaunchData, rocket: RocketData): DetailData {
    return DetailData(
        // 1. Launch 정보 매핑
        id = launch.id,
        title = launch.name,
        createdAt = launch.dateUtc,
        isSuccessLaunched = launch.success ?: false,
        imageUrl = launch.links?.patch?.small ?: "",

        // 2. Rocket 정보 매핑
        rocket = rocket.name,
        description = rocket.description,
        height = rocket.height.meters,
        diameter = rocket.diameter.meters,
        mass = rocket.mass.kg.toDouble(),
        images = rocket.flickrImages,
        wikipedia = rocket.wikipedia
    )
}

@OptIn(ExperimentalTime::class)
fun String.toFormatted(timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
    return try {
        val instant = Instant.parse(this)
        val dateTime = instant.toLocalDateTime(timeZone)
        dateTime.format(LocalDateTime.Format {
            year(); char('.'); monthNumber(); char('.'); day();
            char(' ');
            hour(); char(':'); minute(); char(':'); second()
        })
    } catch (e: Exception) {
        this
    }
}