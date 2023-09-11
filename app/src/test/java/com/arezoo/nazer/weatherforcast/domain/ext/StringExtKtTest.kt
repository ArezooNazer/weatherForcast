package com.arezoo.nazer.weatherforcast.domain.ext

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class StringExtKtTest{

        @Test
        fun `toDayOfWeek returns day of week`() {
            val dayOfWeek = "2021-09-01T12:00".toDayOfWeek()
            assertEquals("Wednesday", dayOfWeek)
        }

        @Test
        fun `toHourOfDay returns hour of day`() {
            val hourOfDay = "2021-09-01T12:00".toHourOfDay()
            assertEquals("12:00", hourOfDay)
        }

        @Test
        fun `isDay returns true when time is between 6 and 19`() {
            val isDay = "2021-09-01T12:00".isDay()
            assertTrue(isDay)
        }

        @Test
        fun `isDay returns false when time is not between 6 and 19`() {
            val isDay = "2021-09-01T20:00".isDay()
            assertFalse(isDay)
        }
}
