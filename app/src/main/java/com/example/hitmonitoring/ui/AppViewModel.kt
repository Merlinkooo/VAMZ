package com.example.hitmonitoring.ui

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.hitmonitoring.HitMonitorinScreen
import com.example.hitmonitoring.R
import com.example.hitmonitoring.data.AppUIState
import com.example.hitmonitoring.data.Control
import com.example.hitmonitoring.database.Database
import com.example.hitmonitoring.network.ConnectionStatus
import com.example.hitmonitoring.network.HitMonitoringApi
import com.example.hitmonitoring.network.checkServerConnection
import com.example.hitmonitoring.network.isInternetAvailable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay

class AppViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AppUIState())

    val uiState: StateFlow<AppUIState> = _uiState.asStateFlow()

    private val _isOnline = MutableStateFlow(ConnectionStatus.CONNECTED)
    val isOnline = _isOnline.asStateFlow()



    init {
        startMonitoring()
    }

    private fun startMonitoring() {
        viewModelScope.launch {
            while (true) {
                _isOnline.value =  if (checkServerConnection())  ConnectionStatus.CONNECTED else ConnectionStatus.SERVER_ERROR
                delay(30000)
            }
        }
    }

    @RequiresPermission(value = "android. permission. ACCESS_NETWORK_STATE")
     fun diagnoseConnection(context: Context)  {
        viewModelScope.launch {
            if (!isInternetAvailable(context)) {
                _isOnline.value = ConnectionStatus.NO_INTERNET
            } else if (!checkServerConnection()) {
                _isOnline.value = ConnectionStatus.SERVER_ERROR
            } else {
                _isOnline.value = ConnectionStatus.CONNECTED
            }
        }
    }

    fun getTagInfo(uid: String ) {
        viewModelScope.launch {
            try {
                val uidToSend = uid.uppercase()

                val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                val response = HitMonitoringApi.retrofitService.getNfcTagInfo(uidToSend)



                _uiState.update {

                    if(response.tagType == 1) {
                        Log.d("UI_STATE_UPDATE", "Previous State: ")
                        it.copy(nameOfGuard = response.tagName)
                    } else{
                        Log.d("UI_STATE_UPDATE", "Previous State:")
                        it.copy(
                            lastControl = Control(
                                nameOfTheObject = response.tagName,
                                timeOfControl = currentTime),
                                newObjectDetected = true
                        )

                    }

                }
            } catch (e: IOException) {
                Log.e("API_ERROR", "Error: ${e.localizedMessage}")
            } catch (e: HttpException) {
                Log.e("API_ERROR", "Error: ${e.localizedMessage}")
            }
        }
    }

    fun eraseImageUri() {
        _uiState.update { currentState ->
            currentState.copy(imageUri = Uri.EMPTY)
        }
    }

    fun updateIncidentDescription(description: String) {
        _uiState.update { currentState ->
            currentState.copy(incidentDescription = description)
        }
    }
    fun uploadPhoto(uri: Uri) {
        _uiState.update { currentState ->
            currentState.copy(imageUri = uri)
        }
    }

    fun clearNewObjectDetected() {
        _uiState.update { currentState ->
            currentState.copy(newObjectDetected = false)
        }
    }

}