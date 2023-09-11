package com.arezoo.nazer.weatherforcast.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arezoo.nazer.weatherforcast.domain.ext.toCelsiusFormat
import com.arezoo.nazer.weatherforcast.domain.model.WeatherHourly
import com.arezoo.nazer.weatherforcast.domain.model.toWeatherType
import com.arezoo.nazer.weatherforcast.presentation.ui.theme.WeatherForcastTheme

@Composable
fun HourlyWeatherCard(
    weatherHourly: WeatherHourly,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.width(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        with(weatherHourly) {
            Text(
                text = temperature.toCelsiusFormat(),
                color = MaterialTheme.colorScheme.onPrimary,
            )

            Image(painter = painterResource(id = dayNightIcon), contentDescription = "weather icon")

            Text(
                text = time,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
@Preview
private fun HourlyWeatherCardPreview() {
    WeatherForcastTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            HourlyWeatherCard(
                weatherHourly = WeatherHourly(
                    temperature = 20,
                    weatherInfo = 0.toWeatherType(),
                    time = "12:00",
                    dayNightIcon = 0.toWeatherType().iconRes
                )
            )

            HourlyWeatherCard(
                weatherHourly = WeatherHourly(
                    temperature = 20,
                    weatherInfo = 0.toWeatherType(),
                    time = "22:00",
                    dayNightIcon = 0.toWeatherType().iconRes
                )
            )
        }
    }
}
