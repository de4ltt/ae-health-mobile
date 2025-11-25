package feo.health.ui.util

import android.Manifest
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import androidx.annotation.RequiresPermission

class HConnectivityChecker(
    private val connectivityManager: ConnectivityManager,
){
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isOnline(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities != null && capabilities.hasCapability(NET_CAPABILITY_INTERNET)
    }
}