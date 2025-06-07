package com.example.hitmonitoring.data

data class AppUIState(
    val nameOfGuard: String = "",
    val lastControls : List<Control> = listOf(),
    val lastControl: Control = Control(),
    val newObjectDetected: Boolean = false
)
