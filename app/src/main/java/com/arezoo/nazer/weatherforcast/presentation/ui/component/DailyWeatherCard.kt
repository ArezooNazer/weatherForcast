package com.arezoo.nazer.weatherforcast.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arezoo.nazer.weatherforcast.R
import com.arezoo.nazer.weatherforcast.domain.ext.toCelsiusFormat
import com.arezoo.nazer.weatherforcast.domain.model.WeatherDaily
import com.arezoo.nazer.weatherforcast.domain.model.WeatherHourly
import com.arezoo.nazer.weatherforcast.domain.model.toWeatherType

@Composable
fun DailyWeatherCard(
    weatherDaily: WeatherDaily,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        with(weatherDaily) {
            Text(
                modifier = Modifier.weight(1f),
                text = day,
                textAlign = TextAlign.Start
            )

            Icon(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = dayAverageTemperatureIcon),
                contentDescription = "weather icon")

            Text(
                modifier = Modifier.weight(1f),
                text = "${lowestTemperature.toCelsiusFormat()} - ${highestTemperature.toCelsiusFormat()}",
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
@Preview
private fun DailyWeatherCardPreview() {
    DailyWeatherCard(
        weatherDaily = WeatherDaily(
            day = "Today",
            currentTemperature = 20,
            lowestTemperature = 20,
            highestTemperature = 30,
            currentWeatherDescription = "Clear",
            dayAverageTemperatureIcon = R.drawable.ic_sunny,
            currentTimeIcon = R.drawable.ic_clear_night,
            weatherOfAllHours = listOf(
                WeatherHourly(
                    temperature = 20,
                    weatherInfo = 0.toWeatherType(),
                    dayNightIcon = R.drawable.ic_clear_night,
                    time = "12:00"
                ),
                WeatherHourly(
                    temperature = 20,
                    weatherInfo = 0.toWeatherType(),
                    dayNightIcon = R.drawable.ic_clear_night,
                    time = "22:00"
                )
            )
        )
    )
}
