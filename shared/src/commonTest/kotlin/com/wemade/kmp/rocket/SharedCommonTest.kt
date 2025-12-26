package com.wemade.kmp.rocket

import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

class SharedCommonTest {

    @Test
    fun formatDateTime() {
        val value = "2007-03-21T01:10:00.000Z".toFormatted(timeZone = TimeZone.UTC)
        assertEquals("2007.03.21 01:10:00", value)
    }
}