package com.arezoo.nazer.weatherforcast.domain.model

import androidx.annotation.DrawableRes

data class WeatherDaily(
    val day: String,
    val currentTemperature: Int,
    val lowestTemperature: Int,
    val highestTemperature: Int,
    val currentWeatherDescription: String,
    @DrawableRes val dayAverageTemperatureIcon: Int,
    @DrawableRes val currentTimeIcon: Int,
    val weatherOfAllHours: List<WeatherHourly>,
)
