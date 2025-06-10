package com.example.hitmonitoring.network

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission


enum class ConnectionStatus(val description: String) {
    CONNECTED("Nezistila sa žiadna chyba"),
    NO_INTERNET("Chýba internetové pripojenie"),
    SERVER_ERROR("Server neodpovedá"),
    INITIAL("")
}


suspend fun checkServerConnection() : Boolean {
    return try {
        HitMonitoringApi.retrofitService.getConnectionStatus()
        true
    } catch (e: Exception) {
        false
    }
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork
    val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
    return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}
