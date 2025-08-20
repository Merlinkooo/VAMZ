package com.example.hitmonitoring.ui.data

import android.location.Location
import android.net.Uri

data class AppUIState(
    val nameOfGuard: String = "",
    val uidOfGuard: String = "",
    val lastControls : List<Control> = listOf(),
    val lastControl: Control = Control(),
    val newObjectDetected: Boolean = false,
    val incidentDescription: String = "",
    val imageUri: Uri = Uri.EMPTY,

)
