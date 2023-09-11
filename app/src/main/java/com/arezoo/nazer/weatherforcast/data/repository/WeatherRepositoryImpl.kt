package com.arezoo.nazer.weatherforcast.data.repository

import com.arezoo.nazer.weatherforcast.data.mapper.toWeatherData
import com.arezoo.nazer.weatherforcast.data.remote.WeatherApi
import com.arezoo.nazer.weatherforcast.domain.model.WeatherData
import com.arezoo.nazer.weatherforcast.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
) : WeatherRepository {

    override suspend fun getWeatherData(latitude: Double, longitude: Double): Result<WeatherData> {
        return runCatching {
            weatherApi.getWeatherData(latitude = latitude, longitude = longitude).toWeatherData()
        }
    }
}
