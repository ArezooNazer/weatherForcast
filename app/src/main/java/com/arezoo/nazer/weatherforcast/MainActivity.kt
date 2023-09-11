package com.arezoo.nazer.weatherforcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arezoo.nazer.weatherforcast.presentation.ui.WeatherScreen
import com.arezoo.nazer.weatherforcast.presentation.ui.theme.WeatherForcastTheme
import com.arezoo.nazer.weatherforcast.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForcastTheme {
                val uiState by viewModel.weatherUiState.collectAsStateWithLifecycle()
                WeatherScreen(uiState)
            }
        }
    }
}
