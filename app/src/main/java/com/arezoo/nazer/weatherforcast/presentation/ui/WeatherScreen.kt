package com.arezoo.nazer.weatherforcast.presentation.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arezoo.nazer.weatherforcast.domain.model.WeatherDaily
import com.arezoo.nazer.weatherforcast.domain.model.WeatherData
import com.arezoo.nazer.weatherforcast.domain.model.WeatherHourly
import com.arezoo.nazer.weatherforcast.domain.model.WeatherUiState
import com.arezoo.nazer.weatherforcast.domain.model.toWeatherType
import com.arezoo.nazer.weatherforcast.presentation.ui.component.DailyWeatherCard
import com.arezoo.nazer.weatherforcast.presentation.ui.component.TodayWeatherCard
import com.arezoo.nazer.weatherforcast.presentation.ui.theme.WeatherForcastTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    weatherUiState: WeatherUiState,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        snackbarHost = { },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                when (weatherUiState) {
                    WeatherUiState.Loading -> Loading()
                    is WeatherUiState.Error -> Error(
                        errorMessage = weatherUiState.errorMessage ?: "Unknown Error",
                        modifier = Modifier.fillMaxSize()
                    )

                    is WeatherUiState.Success -> Success(
                        weatherData = weatherUiState.data,
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }
        }
    )
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = modifier
            .size(48.dp)
            .align(Alignment.Center))
    }
}

@Composable
private fun Error(errorMessage: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Sharp.Info,
            contentDescription = "Error",
            tint = MaterialTheme.colorScheme.error)
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = errorMessage,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun Success(weatherData: WeatherData, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TodayWeatherCard(weatherDaily = weatherData.todayWeatherData)

        LazyColumn(
            modifier = Modifier.padding(top = 16.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(weatherData.weeklyWeatherData.size) {
                DailyWeatherCard(weatherDaily = weatherData.weeklyWeatherData[it])
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun WeatherScreenPreview() {
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
            ),
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
            ),
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
            ),
            WeatherHourly(
                temperature = 20,
                weatherInfo = 0.toWeatherType(),
                time = "12:00",
                dayNightIcon = 0.toWeatherType().iconRes
            ),
        )
    )


    WeatherForcastTheme {
        WeatherScreen(
            weatherUiState = WeatherUiState.Success(
                WeatherData(
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
            )
        )
    }
}
