package com.arezoo.nazer.weatherforcast.presentation.viewmodel

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arezoo.nazer.weatherforcast.domain.model.WeatherUiState
import com.arezoo.nazer.weatherforcast.domain.repository.LocationRepository
import com.arezoo.nazer.weatherforcast.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository,
) : ViewModel() {

    private val _weatherUiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val weatherUiState = _weatherUiState.asStateFlow()

    fun loadWeatherData() {
        viewModelScope.launch {
            locationRepository.getUserCurrentLocation().fold(
                onSuccess = {
                    Log.d(TAG, "loadWeatherData: success = $it")
                    getWeatherDataBasedOnLocation(it)
                },
                onFailure = {
                    Log.d(TAG, "loadWeatherData: error = $it")
                    _weatherUiState.value = WeatherUiState.Error(it.message ?: "Unknown Error")
                }
            )
        }
    }

    private fun getWeatherDataBasedOnLocation(location: Location) {
        viewModelScope.launch {
            weatherRepository.getWeatherData(
                latitude = location.latitude,
                longitude = location.longitude
            ).fold(
                onSuccess = {
                    Log.d(TAG, "loadWeatherData() called success = $it")
                    _weatherUiState.value = WeatherUiState.Success(it)
                },
                onFailure = {
                    Log.d(TAG, "loadWeatherData: error = $it")
                    _weatherUiState.value = WeatherUiState.Error(it.message ?: "Unknown Error")
                }
            )
        }
    }
}

private const val TAG = "WeatherViewModel"
