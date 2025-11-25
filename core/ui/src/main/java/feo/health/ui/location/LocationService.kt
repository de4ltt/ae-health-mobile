package feo.health.ui.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime

object LocationService {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val _locationState = MutableStateFlow<LocationState>(LocationState.Initial)
    val locationState: StateFlow<LocationState> = _locationState.asStateFlow()

    fun initialize(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    fun hasLocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    suspend fun getLastLocation(context: Context): LocationState {
        if (!hasLocationPermission(context)) {
            _locationState.value = LocationState.Error("Location permission not granted")
            return _locationState.value
        }

        return try {
            val location = fusedLocationClient.lastLocation.result
            if (location != null) {
                _locationState.value = LocationState.Success(location, LocalDateTime.now())
                _locationState.value
            } else {
                _locationState.value = LocationState.Error("No last known location available")
                requestLocationUpdates(context)
                _locationState.value
            }
        } catch (e: Exception) {
            _locationState.value = LocationState.Error("Failed to get location: ${e.message}")
            _locationState.value
        }
    }

    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun requestLocationUpdates(context: Context) {
        if (!hasLocationPermission(context)) {
            _locationState.value = LocationState.Error("Location permission not granted")
            return
        }

        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    _locationState.value = LocationState.Success(location, LocalDateTime.now())
                    fusedLocationClient.removeLocationUpdates(this)
                }
            }
        }

        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            _locationState.value = LocationState.Error("Location permission not granted")
        } catch (e: Exception) {
            _locationState.value = LocationState.Error("Failed to request location updates: ${e.message}")
        }
    }

    fun stopLocationUpdates() {
        _locationState.value = LocationState.Initial
        fusedLocationClient.removeLocationUpdates { /* No-op callback */ }
    }

    sealed class LocationState {
        object Initial : LocationState()
        data class Success(val location: Location, val timestamp: LocalDateTime) : LocationState()
        data class Error(val message: String) : LocationState()
    }
}