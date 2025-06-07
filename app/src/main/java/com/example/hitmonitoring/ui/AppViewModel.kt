package com.example.hitmonitoring.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hitmonitoring.HitMonitorinScreen
import com.example.hitmonitoring.data.AppUIState
import com.example.hitmonitoring.data.Control
import com.example.hitmonitoring.network.HitMonitoringApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AppUIState())

    val uiState: StateFlow<AppUIState> = _uiState.asStateFlow()

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

    fun clearNewObjectDetected() {
        _uiState.update { currentState ->
            currentState.copy(newObjectDetected = false)
        }
    }

}