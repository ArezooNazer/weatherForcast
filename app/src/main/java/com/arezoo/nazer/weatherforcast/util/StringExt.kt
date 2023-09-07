package com.arezoo.nazer.weatherforcast.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar

fun String.toDayOfWeek(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
    val daysOfWeek = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    return Calendar.getInstance().run {
        try {
            time = (parser.parse(this@toDayOfWeek))
            daysOfWeek[get(Calendar.DAY_OF_WEEK) - 1]
        } catch (e: Exception) {
            Log.d(TAG, "toDayOfWeek: ${e.message}")
            DEFAULT_DAY
        }
    }
}

fun String.toHourOfDay(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")

    return Calendar.getInstance().run {
        try {
            time = (parser.parse(this@toHourOfDay))
            SimpleDateFormat("HH:mm").format(timeInMillis)
        } catch (e: Exception) {
            Log.d(TAG, "toHour: ${e.message}")
            DEFAULT_HOUR
        }
    }
}

private const val DEFAULT_DAY = ""
private const val DEFAULT_HOUR = "00:00"
private const val TAG = "StringExt"
