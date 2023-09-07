package com.arezoo.nazer.weatherforcast.data.remote

import com.arezoo.nazer.weatherforcast.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast?hourly=temperature_2m,weathercode")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): WeatherDto
}
