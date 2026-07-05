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
import kotlinx.coroutines.suspendCancellableCoroutine
import java.time.LocalDateTime
import kotlin.coroutines.resume

object LocationService {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var activeLocationCallback: LocationCallback? = null
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
    suspend fun getLastLocation(context: Context): LocationState = suspendCancellableCoroutine { continuation ->
        if (!hasLocationPermission(context)) {
            val state = LocationState.Error("Location permission not granted")
            _locationState.value = state
            continuation.resume(state)
            return@suspendCancellableCoroutine
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                val state = if (location != null) {
                    LocationState.Success(location, LocalDateTime.now())
                } else {
                    requestLocationUpdates(context)
                    LocationState.Error("No last known location available")
                }
                _locationState.value = state
                continuation.resume(state)
            }
            .addOnFailureListener { e ->
                val state = LocationState.Error("Failed to get location: ${e.message}")
                _locationState.value = state
                continuation.resume(state)
            }
    }

    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun requestLocationUpdates(context: Context) {
        if (!hasLocationPermission(context)) {
            _locationState.value = LocationState.Error("Location permission not granted")
            return
        }

        activeLocationCallback?.let {
            fusedLocationClient.removeLocationUpdates(it)
        }

        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val callback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    _locationState.value = LocationState.Success(location, LocalDateTime.now())
                    stopLocationUpdates()
                }
            }
        }
        activeLocationCallback = callback

        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                callback,
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
        activeLocationCallback?.let {
            fusedLocationClient.removeLocationUpdates(it)
            activeLocationCallback = null
        }
    }

    sealed class LocationState {
        object Initial : LocationState()
        data class Success(val location: Location, val timestamp: LocalDateTime) : LocationState()
        data class Error(val message: String) : LocationState()
    }
}