package com.arezoo.nazer.weatherforcast.data.repository

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.arezoo.nazer.weatherforcast.domain.ext.hasPermission
import com.arezoo.nazer.weatherforcast.domain.repository.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

@ExperimentalCoroutinesApi
class LocationRepositoryImpl @Inject constructor(
    private val application: Application,
    private val locationClient: FusedLocationProviderClient,
) : LocationRepository {

    @SuppressLint("MissingPermission")
    override suspend fun getUserCurrentLocation(): Result<Location> {

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (locationManager.isLocationNotAvailable()) {
            return Result.failure(Exception("Location is not available, check the location permissions and GPS availability"))
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {

                    if (result == null) {
                        cont.resume(Result.failure(Exception("Location is null")))
                    } else {
                        cont.resume(Result.success(result))
                    }

                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(Result.success(it))
                }
                addOnFailureListener {
                    cont.resume(Result.failure(it))
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }

    private fun LocationManager.isGpsEnabled(): Boolean {
        return isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
            isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun LocationManager.isLocationNotAvailable(): Boolean {
        val hasAccessFineLocationPermission = application.hasPermission(ACCESS_FINE_LOCATION)
        val hasAccessCoarseLocationPermission = application.hasPermission(ACCESS_COARSE_LOCATION)
        return !hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || !isGpsEnabled()
    }
}
