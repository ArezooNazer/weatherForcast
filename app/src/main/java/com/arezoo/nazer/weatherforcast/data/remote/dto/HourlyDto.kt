package com.arezoo.nazer.weatherforcast.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HourlyDto (
    @SerializedName("time") val time: List<String>,
    @SerializedName("temperature_2m") val temperature: List<Float>,
    @SerializedName("weathercode") val weatherCode: List<Int>,
)
