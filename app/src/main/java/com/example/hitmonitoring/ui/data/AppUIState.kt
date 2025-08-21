package com.example.hitmonitoring.ui.data

import android.location.Location
import android.net.Uri
import com.example.hitmonitoring.database.Entities.Checks

data class AppUIState(
    val nameOfGuard: String = "",
    val uidOfGuard: String = "",
    var lastControls : List<Checks> = listOf(),
    val lastControl: Control = Control(),
    val newObjectDetected: Boolean = false,
    val incidentDescription: String = "",
    val imageUri: Uri = Uri.EMPTY,
    val showHistory: Boolean = false

)
