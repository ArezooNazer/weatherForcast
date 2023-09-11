package com.arezoo.nazer.weatherforcast.domain.model

import androidx.annotation.DrawableRes
import com.arezoo.nazer.weatherforcast.R

sealed class WeatherType(
    val weatherDescription: String,
    @DrawableRes val iconRes: Int,
    @DrawableRes val iconNightRes: Int = iconRes,
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

    object HeavyFreezingRain : WeatherType(
        weatherDescription = "Heavy freezing rain",
        iconRes = R.drawable.ic_snow_rainy
    )

    object SlightSnowFall : WeatherType(
        weatherDescription = "Slight snow fall",
        iconRes = R.drawable.ic_snowy
    )

    object ModerateSnowFall : WeatherType(
        weatherDescription = "Moderate snow fall",
        iconRes = R.drawable.ic_snowy
    )

    object HeavySnowFall : WeatherType(
        weatherDescription = "Heavy snow fall",
        iconRes = R.drawable.ic_snowy
    )

    object SnowGrains : WeatherType(
        weatherDescription = "Snow grains",
        iconRes = R.drawable.ic_snow
    )

    object SlightRainShowers : WeatherType(
        weatherDescription = "Slight rain showers",
        iconRes = R.drawable.ic_rainy
    )

    object ModerateRainShowers : WeatherType(
        weatherDescription = "Moderate rain showers",
        iconRes = R.drawable.ic_heavy_rain
    )

    object ViolentRainShowers : WeatherType(
        weatherDescription = "Violent rain showers",
        iconRes = R.drawable.ic_heavy_rain
    )

    object SlightSnowShowers : WeatherType(
        weatherDescription = "Light snow showers",
        iconRes = R.drawable.ic_snowy
    )

    object HeavySnowShowers : WeatherType(
        weatherDescription = "Heavy snow showers",
        iconRes = R.drawable.ic_snowy
    )

    object ModerateThunderstorm : WeatherType(
        weatherDescription = "Moderate thunderstorm",
        iconRes = R.drawable.ic_thunder_storm
    )

    object SlightHailThunderstorm : WeatherType(
        weatherDescription = "Thunderstorm with slight hail",
        iconRes = R.drawable.ic_thunder_rainy
    )

    object HeavyHailThunderstorm : WeatherType(
        weatherDescription = "Thunderstorm with heavy hail",
        iconRes = R.drawable.ic_hail
    )
}

fun Int.toWeatherType(): WeatherType {
    return when (this) {
        0 -> WeatherType.ClearSky
        1 -> WeatherType.MainlyClear
        2 -> WeatherType.PartlyCloudy
        3 -> WeatherType.Overcast
        45 -> WeatherType.Foggy
        48 -> WeatherType.DepositingRimeFog
        51 -> WeatherType.LightDrizzle
        53 -> WeatherType.ModerateDrizzle
        55 -> WeatherType.DenseDrizzle
        56 -> WeatherType.LightFreezingDrizzle
        57 -> WeatherType.DenseFreezingDrizzle
        61 -> WeatherType.SlightRain
        63 -> WeatherType.ModerateRain
        65 -> WeatherType.HeavyRain
        66 -> WeatherType.LightFreezingDrizzle
        67 -> WeatherType.HeavyFreezingRain
        71 -> WeatherType.SlightSnowFall
        73 -> WeatherType.ModerateSnowFall
        75 -> WeatherType.HeavySnowFall
        77 -> WeatherType.SnowGrains
        80 -> WeatherType.SlightRainShowers
        81 -> WeatherType.ModerateRainShowers
        82 -> WeatherType.ViolentRainShowers
        85 -> WeatherType.SlightSnowShowers
        86 -> WeatherType.HeavySnowShowers
        95 -> WeatherType.ModerateThunderstorm
        96 -> WeatherType.SlightHailThunderstorm
        99 -> WeatherType.HeavyHailThunderstorm
        else -> WeatherType.ClearSky
    }
}

fun WeatherType.toWMO(): Int {
    return when (this) {
        WeatherType.ClearSky -> 0
        WeatherType.MainlyClear -> 1
        WeatherType.PartlyCloudy -> 2
        WeatherType.Overcast -> 3
        WeatherType.Foggy -> 45
        WeatherType.DepositingRimeFog -> 48
        WeatherType.LightDrizzle -> 51
        WeatherType.ModerateDrizzle -> 53
        WeatherType.DenseDrizzle -> 55
        WeatherType.LightFreezingDrizzle -> 56
        WeatherType.DenseFreezingDrizzle -> 57
        WeatherType.SlightRain -> 61
        WeatherType.ModerateRain -> 63
        WeatherType.HeavyRain -> 65
        WeatherType.LightFreezingDrizzle -> 66
        WeatherType.HeavyFreezingRain -> 67
        WeatherType.SlightSnowFall -> 71
        WeatherType.ModerateSnowFall -> 73
        WeatherType.HeavySnowFall -> 75
        WeatherType.SnowGrains -> 77
        WeatherType.SlightRainShowers -> 80
        WeatherType.ModerateRainShowers -> 81
        WeatherType.ViolentRainShowers -> 82
        WeatherType.SlightSnowShowers -> 85
        WeatherType.HeavySnowShowers -> 86
        WeatherType.ModerateThunderstorm -> 95
        WeatherType.SlightHailThunderstorm -> 96
        WeatherType.HeavyHailThunderstorm -> 99
    }
}
