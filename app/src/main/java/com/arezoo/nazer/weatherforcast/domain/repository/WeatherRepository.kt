package com.arezoo.nazer.weatherforcast.domain.repository

import com.arezoo.nazer.weatherforcast.domain.model.WeatherData

interface WeatherRepository {

    suspend fun getWeatherData(latitude: Double, longitude: Double): Result<WeatherData>
}
