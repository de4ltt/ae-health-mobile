package feo.health.ui.util

import android.Manifest
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import androidx.annotation.RequiresPermission

/**
 * Helper class that checks if the device is connected to the internet.
 *
 * @property connectivityManager The system service to query network states.
 */
class HConnectivityChecker(
    private val connectivityManager: ConnectivityManager,
){
    /**
     * Checks whether the active network capability has internet access.
     *
     * @return `true` if online, `false` otherwise.
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isOnline(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities != null && capabilities.hasCapability(NET_CAPABILITY_INTERNET)
    }
}