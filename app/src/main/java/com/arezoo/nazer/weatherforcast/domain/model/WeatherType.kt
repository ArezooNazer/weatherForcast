package com.arezoo.nazer.weatherforcast.domain.model

import androidx.annotation.DrawableRes
import com.arezoo.nazer.weatherforcast.R

sealed class WeatherType(
    val weatherDescription: String,
    @DrawableRes val iconRes: Int,
    @DrawableRes val iconNightRes: Int = iconRes
) {
    object ClearSky : WeatherType(
        weatherDescription = "Clear sky",
        iconRes = R.drawable.ic_sunny,
        iconNightRes = R.drawable.ic_clear_night
    )
    object MainlyClear : WeatherType(
        weatherDescription = "Mainly clear",
        iconRes = R.drawable.ic_mainly_clear,
        iconNightRes = R.drawable.ic_mainly_clear_night
    )
    object PartlyCloudy : WeatherType(
        weatherDescription = "Partly cloudy",
        iconRes = R.drawable.ic_partialy_cloudy,
        iconNightRes = R.drawable.ic_partialy_cloudy_night
    )
    object Overcast : WeatherType(
        weatherDescription = "Overcast",
        iconRes = R.drawable.ic_cloudy
    )
    object Foggy : WeatherType(
        weatherDescription = "Foggy",
        iconRes = R.drawable.ic_foggy
    )
    object DepositingRimeFog : WeatherType(
        weatherDescription = "Depositing rime fog",
        iconRes = R.drawable.ic_foggy
    )
    object LightDrizzle : WeatherType(
        weatherDescription = "Light drizzle",
        iconRes = R.drawable.ic_light_drizzle,
        iconNightRes = R.drawable.ic_light_drizzle_night
    )
    object ModerateDrizzle : WeatherType(
        weatherDescription = "Moderate drizzle",
        iconRes = R.drawable.ic_rainy
    )
    object DenseDrizzle : WeatherType(
        weatherDescription = "Dense drizzle",
        iconRes = R.drawable.ic_drizzle
    )
    object LightFreezingDrizzle : WeatherType(
        weatherDescription = "Slight freezing drizzle",
        iconRes = R.drawable.ic_rainy
    )
    object DenseFreezingDrizzle : WeatherType(
        weatherDescription = "Dense freezing drizzle",
        iconRes = R.drawable.ic_snow_rainy
    )
    object SlightRain : WeatherType(
        weatherDescription = "Slight rain",
        iconRes = R.drawable.ic_rainy
    )
    object ModerateRain : WeatherType(
        weatherDescription = "Rainy",
        iconRes = R.drawable.ic_rainy
    )
    object HeavyRain : WeatherType(
        weatherDescription = "Heavy rain",
        iconRes = R.drawable.ic_heavy_rain
    )
    object HeavyFreezingRain: WeatherType(
        weatherDescription = "Heavy freezing rain",
        iconRes = R.drawable.ic_snow_rainy
    )
    object SlightSnowFall: WeatherType(
        weatherDescription = "Slight snow fall",
        iconRes = R.drawable.ic_snowy
    )
    object ModerateSnowFall: WeatherType(
        weatherDescription = "Moderate snow fall",
        iconRes = R.drawable.ic_snowy
    )
    object HeavySnowFall: WeatherType(
        weatherDescription = "Heavy snow fall",
        iconRes = R.drawable.ic_snowy
    )
    object SnowGrains: WeatherType(
        weatherDescription = "Snow grains",
        iconRes = R.drawable.ic_snow
    )
    object SlightRainShowers: WeatherType(
        weatherDescription = "Slight rain showers",
        iconRes = R.drawable.ic_rainy
    )
    object ModerateRainShowers: WeatherType(
        weatherDescription = "Moderate rain showers",
        iconRes = R.drawable.ic_heavy_rain
    )
    object ViolentRainShowers: WeatherType(
        weatherDescription = "Violent rain showers",
        iconRes = R.drawable.ic_heavy_rain
    )
    object SlightSnowShowers: WeatherType(
        weatherDescription = "Light snow showers",
        iconRes = R.drawable.ic_snowy
    )
    object HeavySnowShowers: WeatherType(
        weatherDescription = "Heavy snow showers",
        iconRes = R.drawable.ic_snowy
    )
    object ModerateThunderstorm: WeatherType(
        weatherDescription = "Moderate thunderstorm",
        iconRes = R.drawable.ic_thunder_storm
    )
    object SlightHailThunderstorm: WeatherType(
        weatherDescription = "Thunderstorm with slight hail",
        iconRes = R.drawable.ic_thunder_rainy
    )
    object HeavyHailThunderstorm: WeatherType(
        weatherDescription = "Thunderstorm with heavy hail",
        iconRes = R.drawable.ic_hail
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}
