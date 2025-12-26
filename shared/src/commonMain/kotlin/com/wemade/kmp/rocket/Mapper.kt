package com.wemade.kmp.rocket

import com.wemade.kmp.rocket.model.ListData
import com.wemade.kmp.rocket.model.DetailData
import com.wemade.kmp.rocket.repository.model.RocketLaunchData
import com.wemade.kmp.rocket.repository.model.RocketData
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime


fun RocketLaunchData.toDomain(): ListData = ListData(
    id = id,
    rocket = rocketId,
    title = name,
    launchDate = launchDateUTC.toFormatted(),
    imageUrl = link.patch?.small,
    isSuccessLaunched = launchSuccess ?: false
)

fun RocketData.toDetailData(): DetailData = DetailData(
    id = this.id,
    rocket = this.type,
    title = this.name,
    description = this.description,
    createdAt = this.firstFlight, // "2018-02-06" 형태의 String 유지

    // flickrImages 리스트의 첫 번째를 대표 이미지로 사용. 비어있을 경우 빈 문자열 안전 처리
    imageUrl = this.flickrImages.firstOrNull() ?: "",

    isSuccessLaunched = this.active, // API의 'active' 상태를 매핑

    height = this.height.meters,
    diameter = this.diameter.meters,

    // RocketData의 mass.kg는 Int, DetailData는 Double이므로 형변환
    mass = this.mass.kg.toDouble(),

    images = this.flickrImages,
    wikipedia = this.wikipedia
)

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