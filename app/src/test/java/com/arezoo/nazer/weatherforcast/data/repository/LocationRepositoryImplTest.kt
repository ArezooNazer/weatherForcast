package com.arezoo.nazer.weatherforcast.data.repository

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationManager
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.content.ContextCompat
import com.arezoo.nazer.weatherforcast.util.MainCoroutineRule
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Task
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val locationManager: LocationManager = mock()
    private val application: Application = mock()
    private val locationClient: FusedLocationProviderClient = mock()
    private val locationRepository = LocationRepositoryImpl(application, locationClient)

    @Test
    fun `getUserCurrentLocation returns location when available`() = runTest {
        val mockTask: Task<Location> = mock()

        whenever(application.getSystemService(Context.LOCATION_SERVICE)).thenReturn(locationManager)
        whenever(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)).thenReturn(true)

        whenever(ContextCompat.checkSelfPermission(application, ACCESS_FINE_LOCATION)).thenReturn(PERMISSION_GRANTED)
        whenever(ContextCompat.checkSelfPermission(application, ACCESS_COARSE_LOCATION)).thenReturn(PERMISSION_GRANTED)


        whenever(locationClient.lastLocation).thenReturn(mockTask)
        whenever(mockTask.isComplete).thenReturn(true)
        whenever(mockTask.result).thenReturn(mock())

        val result = locationRepository.getUserCurrentLocation()

        assert(result.isSuccess)
        assert(result.getOrNull() != null)
    }

    @Test
    fun `getUserCurrentLocation returns permission error when ACCESS_FINE_LOCATION is not granted`() = runTest {

        whenever(application.getSystemService(Context.LOCATION_SERVICE)).thenReturn(locationManager)

        whenever(ContextCompat.checkSelfPermission(application, ACCESS_FINE_LOCATION)).thenReturn(PERMISSION_DENIED)
        whenever(ContextCompat.checkSelfPermission(application, ACCESS_COARSE_LOCATION)).thenReturn(PERMISSION_GRANTED)

        val result = locationRepository.getUserCurrentLocation()

        assert(result.isFailure)
    }

    @Test
    fun `getUserCurrentLocation returns permission error when ACCESS_COARSE_LOCATION is not granted`() = runTest {

        whenever(application.getSystemService(Context.LOCATION_SERVICE)).thenReturn(locationManager)

        whenever(ContextCompat.checkSelfPermission(application, ACCESS_FINE_LOCATION)).thenReturn(PERMISSION_GRANTED)
        whenever(ContextCompat.checkSelfPermission(application, ACCESS_COARSE_LOCATION)).thenReturn(PERMISSION_DENIED)

        val result = locationRepository.getUserCurrentLocation()

        assert(result.isFailure)
    }

    @Test
    fun `getUserCurrentLocation returns permission error when gps is not enabled`() = runTest {

        whenever(application.getSystemService(Context.LOCATION_SERVICE)).thenReturn(locationManager)
        whenever(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)).thenReturn(false)
        whenever(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)).thenReturn(false)

        whenever(ContextCompat.checkSelfPermission(application, ACCESS_FINE_LOCATION)).thenReturn(PERMISSION_GRANTED)
        whenever(ContextCompat.checkSelfPermission(application, ACCESS_COARSE_LOCATION)).thenReturn(PERMISSION_GRANTED)

        val result = locationRepository.getUserCurrentLocation()

        assert(result.isFailure)
    }

    @Test
    fun `getUserCurrentLocation returns error when location is null`() = runTest {
        val mockTask: Task<Location> = mock()

        whenever(application.getSystemService(Context.LOCATION_SERVICE)).thenReturn(locationManager)
        whenever(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)).thenReturn(true)

        whenever(ContextCompat.checkSelfPermission(application, ACCESS_FINE_LOCATION)).thenReturn(PERMISSION_GRANTED)
        whenever(ContextCompat.checkSelfPermission(application, ACCESS_COARSE_LOCATION)).thenReturn(PERMISSION_GRANTED)


        whenever(locationClient.lastLocation).thenReturn(mockTask)
        whenever(mockTask.isComplete).thenReturn(true)
        whenever(mockTask.result).thenReturn(null)

        val result = locationRepository.getUserCurrentLocation()

        assert(result.isFailure)
    }
}
