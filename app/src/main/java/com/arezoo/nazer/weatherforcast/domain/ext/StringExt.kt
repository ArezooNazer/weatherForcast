package com.arezoo.nazer.weatherforcast.domain.ext

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar

fun String.toDayOfWeek(): String {
    val parser = SimpleDateFormat(DEFAULT_TIME_FORMAT)
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
    val parser = SimpleDateFormat(DEFAULT_TIME_FORMAT)

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

fun String.isDay(): Boolean {
    val parser = SimpleDateFormat(DEFAULT_TIME_FORMAT)

    return Calendar.getInstance().run {
        try {
            time = (parser.parse(this@isDay))
            get(Calendar.HOUR_OF_DAY) in 6..19
        } catch (e: Exception) {
            Log.d(TAG, "isDay: ${e.message}")
            false
        }
    }
}

private const val DEFAULT_DAY = ""
private const val DEFAULT_HOUR = "00:00"
private const val DEFAULT_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm"
private const val TAG = "StringExt"
