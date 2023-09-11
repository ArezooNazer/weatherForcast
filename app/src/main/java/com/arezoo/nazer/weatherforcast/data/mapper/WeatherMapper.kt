package com.arezoo.nazer.weatherforcast.data.mapper

import com.arezoo.nazer.weatherforcast.R
import com.arezoo.nazer.weatherforcast.data.remote.dto.WeatherDto
import com.arezoo.nazer.weatherforcast.domain.ext.isDay
import com.arezoo.nazer.weatherforcast.domain.ext.toDayOfWeek
import com.arezoo.nazer.weatherforcast.domain.ext.toHourOfDay
import com.arezoo.nazer.weatherforcast.domain.model.WeatherDaily
import com.arezoo.nazer.weatherforcast.domain.model.WeatherData
import com.arezoo.nazer.weatherforcast.domain.model.WeatherHourly
import com.arezoo.nazer.weatherforcast.domain.model.toWMO
import com.arezoo.nazer.weatherforcast.domain.model.toWeatherType
import java.text.SimpleDateFormat
import java.util.Date

fun WeatherDto.toWeatherData(): WeatherData {

    val hourlyWeathersOfNextDays = mutableListOf<WeatherHourly>().apply {
        for (i in 0 until hourly.time.size) {
            val weatherInfo = hourly.weatherCode[i].toWeatherType()

            add(
                WeatherHourly(
                    temperature = hourly.temperature[i].toInt(),
                    weatherInfo = weatherInfo,
                    time = hourly.time[i].toHourOfDay(),
                    dayNightIcon = if (hourly.time[i].isDay()) weatherInfo.iconRes else weatherInfo.iconNightRes
                )
            )
        }
    }

    val currentHour = getCurrentTime()
    val weaklyWeatherData = hourlyWeathersOfNextDays.chunked(CHUNK_SIZE).mapIndexed { index, list ->
        WeatherDaily(
            day = hourly.time[index * CHUNK_SIZE].toDayOfWeek(),
            currentTemperature = list.getCurrentTemperature(currentHour),
            lowestTemperature = list.minOf { it.temperature },
            highestTemperature = list.maxOf { it.temperature },
            currentWeatherDescription = list.getCurrentTemperatureDescription(currentHour),
            dayAverageTemperatureIcon = list.map { it.weatherInfo.toWMO() }.average().toInt().run {
                toWeatherType().iconRes
            },
            currentTimeIcon = list.getCurrentTimeIcon(currentHour),
            weatherOfAllHours = list
        )
    }

    return WeatherData(
        todayWeatherData = weaklyWeatherData.first().copy(day = "Today"),
        weeklyWeatherData = weaklyWeatherData
    )
}

private fun List<WeatherHourly>.getCurrentTemperature(currentHour: String): Int {
    return find { it.time.startsWith(currentHour) }?.temperature ?: 0
}

private fun List<WeatherHourly>.getCurrentTimeIcon(currentHour: String): Int {
    return find { it.time.startsWith(currentHour) }?.dayNightIcon ?: R.drawable.ic_sunny
}

private fun List<WeatherHourly>.getCurrentTemperatureDescription(currentHour: String): String {
    return find { it.time.startsWith(currentHour) }?.weatherInfo?.weatherDescription ?: ""
}

private fun getCurrentTime(): String {
    return SimpleDateFormat("HH").format(Date())
}

private const val CHUNK_SIZE = 24

