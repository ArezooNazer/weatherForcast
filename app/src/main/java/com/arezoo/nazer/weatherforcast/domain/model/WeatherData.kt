package com.arezoo.nazer.weatherforcast.domain.model

data class WeatherData(
    val todayWeatherData: WeatherDaily,
    val weeklyWeatherData: List<WeatherDaily>,
)
