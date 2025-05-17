package com.example.hitmonitoring.ui

import androidx.lifecycle.ViewModel
import com.example.hitmonitoring.data.AppUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
.
class AppViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AppUIState())
    val uiState: StateFlow<AppUIState> = _uiState.asStateFlow()



}