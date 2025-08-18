package com.example.hitmonitoring.ui

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hitmonitoring.data.NetworkTagInfoRepository
import com.example.hitmonitoring.ui.data.AppUIState
import com.example.hitmonitoring.ui.data.Control
import com.example.hitmonitoring.database.AppDatabase
import com.example.hitmonitoring.database.Converters
import com.example.hitmonitoring.database.DatabaseProvider
import com.example.hitmonitoring.database.Entities.Check
import com.example.hitmonitoring.database.Entities.User
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
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay

class AppViewModel: ViewModel() {

    private val tagInfoRepository: NetworkTagInfoRepository = NetworkTagInfoRepository()
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

    fun getTagInfo(uid: String , context: Context, database: AppDatabase) {
        val appDatabase: AppDatabase = DatabaseProvider.getDatabase(context)

        viewModelScope.launch {
            try {
                val uidToSend = uid.uppercase()

                val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                val response = tagInfoRepository.getTagInfo(uid)



                _uiState.update {

                    if(response.tagType == 1) {
                        Log.d("UI_STATE_UPDATE", "Previous State: ")
                        it.copy(nameOfGuard = response.tagName)

                    } else{
                        Log.d("UI_STATE_UPDATE", "Previous State:")
                        appDatabase.checkDao().insertAll(
                            Check(time= currentTime,
                                guardID = _uiState.value.nameOfGuard,
                                latitude = ,
                                longitude = ,
                                ))
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
                val guard: User? = appDatabase.userDao().findByTag(uid)
                if (guard !=null) {
                    _uiState.update {
                        it.copy(nameOfGuard = guard.firstName + " " + guard.lastName)
                    }
                }
                DatabaseProvider.getDatabase(context)
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