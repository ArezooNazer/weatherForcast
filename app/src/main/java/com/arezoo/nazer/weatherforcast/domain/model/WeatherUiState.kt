package com.arezoo.nazer.weatherforcast.domain.model

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    class Success(val data: WeatherData) : WeatherUiState()
    class Error(val errorMessage: String) : WeatherUiState()
}
