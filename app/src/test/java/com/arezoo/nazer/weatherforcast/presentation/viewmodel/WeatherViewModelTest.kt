package com.arezoo.nazer.weatherforcast.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arezoo.nazer.weatherforcast.domain.model.WeatherDaily
import com.arezoo.nazer.weatherforcast.domain.model.WeatherData
import com.arezoo.nazer.weatherforcast.domain.model.WeatherHourly
import com.arezoo.nazer.weatherforcast.domain.model.WeatherUiState
import com.arezoo.nazer.weatherforcast.domain.model.toWeatherType
import com.arezoo.nazer.weatherforcast.domain.repository.LocationRepository
import com.arezoo.nazer.weatherforcast.domain.repository.WeatherRepository
import com.arezoo.nazer.weatherforcast.util.MainCoroutineRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val weatherRepository: WeatherRepository = mock()
    private val locationRepository: LocationRepository = mock()

    private val weatherViewModel = WeatherViewModel(weatherRepository, locationRepository)

    @Test
    fun `loadWeatherData called with success`() = runTest {

        val dailyWeather = WeatherDaily(
            day = "Today",
            currentTemperature = 20,
            lowestTemperature = 10,
            highestTemperature = 30,
            currentWeatherDescription = "Sunny",
            dayAverageTemperatureIcon = 0.toWeatherType().iconRes,
            currentTimeIcon = 0.toWeatherType().iconRes,
            weatherOfAllHours = listOf(
                WeatherHourly(
                    temperature = 20,
                    weatherInfo = 0.toWeatherType(),
                    time = "12:00",
                    dayNightIcon = 0.toWeatherType().iconRes
                ),
                WeatherHourly(
                    temperature = 20,
                    weatherInfo = 0.toWeatherType(),
                    time = "12:00",
                    dayNightIcon = 0.toWeatherType().iconRes
                )
            )
        )

        val fakeWeatherData = WeatherData(
            todayWeatherData = dailyWeather,
            weeklyWeatherData = listOf(
                dailyWeather,
                dailyWeather,
                dailyWeather,
                dailyWeather,
                dailyWeather,
                dailyWeather,
                dailyWeather,
            )
        )

        whenever(
            locationRepository.getUserCurrentLocation()
        ).thenReturn(Result.success(mock()))

        whenever(
            weatherRepository.getWeatherData(any(), any())
        ).thenReturn(Result.success(fakeWeatherData))


        weatherViewModel.loadWeatherData()

        verify(locationRepository).getUserCurrentLocation()
        verify(weatherRepository).getWeatherData(any(), any())
        assert(weatherViewModel.weatherUiState.value is WeatherUiState.Success)
    }

    @Test
    fun `loadWeatherData called with locationRepository failure`() = runTest {
        whenever(
            locationRepository.getUserCurrentLocation()
        ).thenReturn(Result.failure(Exception("Location Error")))

        weatherViewModel.loadWeatherData()

        verify(locationRepository).getUserCurrentLocation()
        verifyNoMoreInteractions(weatherRepository)
        assert(weatherViewModel.weatherUiState.value is WeatherUiState.Error)
    }

    @Test
    fun `loadWeatherData called with weatherRepository failure`() = runTest {
        whenever(
            locationRepository.getUserCurrentLocation()
        ).thenReturn(Result.success(mock()))

        whenever(
            weatherRepository.getWeatherData(any(), any())
        ).thenReturn(Result.failure(Exception("Weather Error")))

        weatherViewModel.loadWeatherData()

        verify(locationRepository).getUserCurrentLocation()
        verify(weatherRepository).getWeatherData(any(), any())
        assert(weatherViewModel.weatherUiState.value is WeatherUiState.Error)
    }
}
