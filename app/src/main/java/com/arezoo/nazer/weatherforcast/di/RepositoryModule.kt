package com.arezoo.nazer.weatherforcast.di

import com.arezoo.nazer.weatherforcast.data.repository.WeatherRepositoryImpl
import com.arezoo.nazer.weatherforcast.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl,
    ): WeatherRepository
}
