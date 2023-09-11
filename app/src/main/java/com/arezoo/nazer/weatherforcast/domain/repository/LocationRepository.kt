package com.arezoo.nazer.weatherforcast.domain.repository

import android.location.Location

interface LocationRepository {

    suspend fun getUserCurrentLocation(): Result<Location>
}
