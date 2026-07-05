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

/**
 * Service singleton wrapping Google Play Services [FusedLocationProviderClient] to query,
 * request, and track user geolocation coordinates.
 */
object LocationService {
    /**
     * Backing API client instance used to fetch geo coordinates.
     */
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    /**
     * Callback handler listening to active location update cycles.
     */
    private var activeLocationCallback: LocationCallback? = null

    /**
     * Internal location state holder flow.
     */
    private val _locationState = MutableStateFlow<LocationState>(LocationState.Initial)

    /**
     * Read-only StateFlow observing the current device location state.
     */
    val locationState: StateFlow<LocationState> = _locationState.asStateFlow()

    /**
     * Initializes the fused location provider client context.
     *
     * @param context Application/Activity context descriptor.
     */
    fun initialize(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    /**
     * Checks if location permission has been explicitly granted by the user.
     *
     * @param context Active system context context.
     * @return `true` if fine or coarse location is granted, `false` otherwise.
     */
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

    /**
     * Fetches the last known device location. If unavailable, falls back to requesting new updates.
     *
     * @param context Active system context context.
     * @return The updated [LocationState] result.
     */
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

    /**
     * Requests high-accuracy coordinates updates using a timed callback loop.
     *
     * @param context Active system context context.
     */
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

    /**
     * Stops requesting coordinates updates and resets the location state to initial.
     */
    fun stopLocationUpdates() {
        _locationState.value = LocationState.Initial
        activeLocationCallback?.let {
            fusedLocationClient.removeLocationUpdates(it)
            activeLocationCallback = null
        }
    }

    /**
     * Sealed class wrapper representing the current device location retrieval state.
     */
    sealed class LocationState {
        /**
         * Represents the initial/idle geolocation state.
         */
        object Initial : LocationState()

        /**
         * Represents a successful geolocation fetch.
         *
         * @property location The fetched Android location model.
         * @property timestamp The timestamp of the location fetch.
         */
        data class Success(val location: Location, val timestamp: LocalDateTime) : LocationState()

        /**
         * Represents a failed geolocation fetch.
         *
         * @property message Error details.
         */
        data class Error(val message: String) : LocationState()
    }
}