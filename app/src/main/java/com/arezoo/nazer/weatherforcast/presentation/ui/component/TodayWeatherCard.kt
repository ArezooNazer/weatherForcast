package com.arezoo.nazer.weatherforcast.presentation.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arezoo.nazer.weatherforcast.R
import com.arezoo.nazer.weatherforcast.domain.ext.toCelsiusFormat
import com.arezoo.nazer.weatherforcast.domain.model.WeatherDaily
import com.arezoo.nazer.weatherforcast.domain.model.WeatherHourly
import com.arezoo.nazer.weatherforcast.domain.model.toWeatherType
import com.arezoo.nazer.weatherforcast.presentation.ui.theme.WeatherForcastTheme

@Composable
fun TodayWeatherCard(
    weatherDaily: WeatherDaily,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(id = weatherDaily.currentTimeIcon),
                contentDescription = "weather icon"
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = weatherDaily.day,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    modifier= Modifier.padding(vertical = 8.dp),
                    text = weatherDaily.currentTemperature.toCelsiusFormat(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = ExtraBold
                )

                Text(
                    text = weatherDaily.currentWeatherDescription,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            items(weatherDaily.weatherOfAllHours.size) { index ->
                HourlyWeatherCard(weatherDaily.weatherOfAllHours[index])
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun TodayWeatherCardPreview() {
    WeatherForcastTheme {
        TodayWeatherCard(
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
                        time = "00:00"
                    ),
                    WeatherHourly(
                        temperature = 20,
                        weatherInfo = 0.toWeatherType(),
                        dayNightIcon = R.drawable.ic_clear_night,
                        time = "01:00"
                    ),
                    WeatherHourly(
                        temperature = 20,
                        weatherInfo = 0.toWeatherType(),
                        dayNightIcon = R.drawable.ic_clear_night,
                        time = "02:00"
                    ),
                    WeatherHourly(
                        temperature = 20,
                        weatherInfo = 0.toWeatherType(),
                        dayNightIcon = R.drawable.ic_clear_night,
                        time = "03:00"
                    ),
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
                        time = "12:00"
                    ),
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
                        time = "12:00"
                    ),
                )
            )
        )
    }
}
