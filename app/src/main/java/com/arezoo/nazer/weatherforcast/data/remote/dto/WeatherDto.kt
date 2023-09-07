package com.arezoo.nazer.weatherforcast.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("hourly") val hourly: HourlyDto,
)
