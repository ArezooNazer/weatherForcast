package com.arezoo.nazer.weatherforcast.data.repository

import com.arezoo.nazer.weatherforcast.data.remote.WeatherApi
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryImplTest {

    private val weatherApi: WeatherApi = mock()
    private val weatherRepository = WeatherRepositoryImpl(weatherApi)

    @Test
    fun `getWeatherData calls weather api getWeatherData `() = runTest {
        weatherRepository.getWeatherData(0.0, 0.0)

        verify(weatherApi).getWeatherData(0.0, 0.0)
    }
}
