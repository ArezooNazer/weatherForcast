package com.arezoo.nazer.weatherforcast.domain.model

data class WeatherHourly(
    val weatherInfo: WeatherType,
    val temperature: Float,
    val time: String,
)
