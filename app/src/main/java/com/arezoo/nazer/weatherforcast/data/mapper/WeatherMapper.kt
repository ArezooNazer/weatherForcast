package com.arezoo.nazer.weatherforcast.data.mapper

import com.arezoo.nazer.weatherforcast.data.remote.dto.WeatherDto
import com.arezoo.nazer.weatherforcast.domain.model.WeatherDaily
import com.arezoo.nazer.weatherforcast.domain.model.WeatherData
import com.arezoo.nazer.weatherforcast.domain.model.WeatherHourly
import com.arezoo.nazer.weatherforcast.domain.model.WeatherType
import com.arezoo.nazer.weatherforcast.util.toDayOfWeek
import com.arezoo.nazer.weatherforcast.util.toHourOfDay

fun WeatherDto.toWeatherData(): WeatherData {

    val hourlyWeathersOfNextDays = mutableListOf<WeatherHourly>().apply {
        for (i in 0 until hourly.time.size) {
            add(
                WeatherHourly(
                    temperature = hourly.temperature[i],
                    weatherInfo = WeatherType.fromWMO(hourly.weatherCode[i]),
                    time = hourly.time[i].toHourOfDay()
                )
            )
        }
    }

    val weaklyWeatherData = hourlyWeathersOfNextDays.chunked(CHUNK_SIZE).mapIndexed { index, list ->
        WeatherDaily(
            day = hourly.time[index * CHUNK_SIZE].toDayOfWeek(),
            weatherOfAllHours = list
        )
    }

    return WeatherData(
        todayWeatherData = weaklyWeatherData.first().copy(day = "Today"),
        weeklyWeatherData = weaklyWeatherData
    )
}

private const val CHUNK_SIZE = 24

