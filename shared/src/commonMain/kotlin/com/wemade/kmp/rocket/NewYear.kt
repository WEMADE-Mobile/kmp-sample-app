package com.wemade.kmp.rocket

import kotlinx.datetime.*
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun daysUntilNewYear(): Int {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val closestNewYear = LocalDate(today.year + 1, 1, 1)
    return today.daysUntil(closestNewYear)
}

fun daysPhrase(): String = "There are only ${daysUntilNewYear()} days left until New Year! 🎆"