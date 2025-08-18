package com.example.hitmonitoring.data

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.hitmonitoring.Manifest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationRepository(private val context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    suspend fun getLastLocation(): Location? {
        // Tu implementuj logiku na získanie polohy
        // POZNÁMKA: Použitie suspend funkcie a coroutines je lepšie pre asynchrónne operácie
        return suspendCoroutine { continuation ->
            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        continuation.resume(location)
                    }
                    .addOnFailureListener { e ->
                        Log.e("LOCATION", "Error getting location: ${e.message}", e)
                        continuation.resume(null)
                    }
            } else {
                continuation.resume(null)
            }
        }
    }
}