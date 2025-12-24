package com.wemade.kmp.rocket

import com.wemade.kmp.rocket.model.ListData
import com.wemade.kmp.rocket.repository.model.RocketLaunchData
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