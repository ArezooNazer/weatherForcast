package com.arezoo.nazer.weatherforcast.domain.model

data class WeatherDaily(
    val day: String,
    val weatherOfAllHours: List<WeatherHourly>,
)
