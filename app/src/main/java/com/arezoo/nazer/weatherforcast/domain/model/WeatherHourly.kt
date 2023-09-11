package com.arezoo.nazer.weatherforcast.domain.model

import androidx.annotation.DrawableRes

data class WeatherHourly(
    val weatherInfo: WeatherType,
    val temperature: Int,
    val time: String,
    @DrawableRes val dayNightIcon: Int,
)
