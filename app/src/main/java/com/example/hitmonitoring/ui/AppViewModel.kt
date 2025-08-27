package com.example.hitmonitoring.ui

import android.content.Context
import android.location.Location
import android.net.Uri
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.hitmonitoring.HitMonitoringApplication
import com.example.hitmonitoring.data.CheckInfoRepository
import com.example.hitmonitoring.data.CheckInfoRepositoryImpl
import com.example.hitmonitoring.data.LocationRepository
import com.example.hitmonitoring.data.NetworkTagInfoRepository
import com.example.hitmonitoring.data.ReportInfoRepositoryImpl
import com.example.hitmonitoring.ui.data.AppUIState
import com.example.hitmonitoring.ui.data.Control
import com.example.hitmonitoring.database.AppDatabase
import com.example.hitmonitoring.database.Converters
import com.example.hitmonitoring.database.DatabaseProvider
import com.example.hitmonitoring.database.Entities.Checks
import com.example.hitmonitoring.database.Entities.Report
import com.example.hitmonitoring.database.Entities.User
import com.example.hitmonitoring.network.ConnectionStatus

import com.example.hitmonitoring.network.checkServerConnection
import com.example.hitmonitoring.network.isInternetAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
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
import kotlinx.coroutines.flow.Flow
import okhttp3.Dispatcher

class AppViewModel(
    private val tagInfoRepository: NetworkTagInfoRepository,
    private val checkInfoRepository: CheckInfoRepository,
    private val locationRepository: LocationRepository,
    private val reportRepository: ReportInfoRepositoryImpl
): ViewModel() {


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
                _isOnline.value =  if (checkServerConnection(tagInfoRepository))  ConnectionStatus.CONNECTED else ConnectionStatus.SERVER_ERROR
                delay(30000)
            }
        }
    }

    @RequiresPermission(value = "android. permission. ACCESS_NETWORK_STATE")
     fun diagnoseConnection(context: Context)  {
        viewModelScope.launch {
            if (!isInternetAvailable(context)) {
                _isOnline.value = ConnectionStatus.NO_INTERNET
            } else if (checkServerConnection(tagInfoRepository)) {
                _isOnline.value = ConnectionStatus.SERVER_ERROR
            } else {
                _isOnline.value = ConnectionStatus.CONNECTED
            }
        }
    }

    fun getTagInfo(uid: String) {


        viewModelScope.launch {
            try {


                val currentTime = SimpleDateFormat("HH:mm:ss dd.MM.yy", Locale.getDefault()).format(Date())
                val response = tagInfoRepository.getTagInfo(uid.toUpperCase(androidx.compose.ui.text.intl.Locale.current))
                if(response.tagType == 1) {
                        Log.d("UI_STATE_UPDATE", "Previous State: ")
                        _uiState.update {
                            it.copy(nameOfGuard = response.tagName, uidOfGuard = uid)
                        }

                    } else{

                        val lastLocation : Location? = locationRepository.getLastLocation()
                        Log.d("UI_STATE_UPDATE", "Previous State:")
                        viewModelScope.launch(Dispatchers.IO) {
                            checkInfoRepository.saveControl(
                                Control(
                                    response.tagName, uid,
                                    currentTime,
                                    longitude =  if (lastLocation?.longitude != null) lastLocation?.longitude!!.toDouble() else 0.0,
                                    latitude = if (lastLocation?.latitude != null) lastLocation?.latitude!!.toDouble() else 0.0,
                                ), _uiState.value.nameOfGuard
                            )
                        }

                        _uiState.update {
                            it.copy(
                                lastControl = Control(
                                    nameOfTheObject = response.tagName,
                                    uidOfTheObject = uid,
                                    timeOfControl = currentTime
                                ),
                                newObjectDetected = true
                            )

                    }

                }


            } catch (e: IOException) {
                Log.e("API_ERROR", "Error: ${e.localizedMessage}")

            } catch (e: HttpException) {
//                //val guard: User? = appDatabase.userDao().findByTag(uid)
//                if (guard !=null) {
//                    _uiState.update {
//                        it.copy(nameOfGuard = guard.firstName + " " + guard.lastName)
//                    }
//                }

                Log.e("API_ERROR", "Error: ${e.localizedMessage}")
            }
            catch (e: Exception)  {
                Log.e("Error", "Error: ${e.localizedMessage}")
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
    fun createReportWhileControl() {
        _uiState.update { currentState ->
            currentState.copy(incidentWhileControl = true)
        }
    }



    fun clearNewObjectDetected() {
        _uiState.update { currentState ->
            currentState.copy(newObjectDetected = false)
        }
    }

     fun getLastChecks(): Flow<List<Checks>> {

        return checkInfoRepository.getControlsInfo()
    }
    fun changeShowHistoryStatus() {
        _uiState.update { currentState ->
            currentState.copy(showHistory = !currentState.showHistory)
        }
    }

    fun sendReport() {
        viewModelScope.launch(Dispatchers.IO) {
            if(_uiState.value.incidentWhileControl) {
                reportRepository.saveReport(
                    Report(
                        time = uiState.value.lastControl.timeOfControl,
                        guardID = uiState.value.uidOfGuard,
                        latitude = uiState.value.lastControl.latitude,
                        longitude = uiState.value.lastControl.longitude ,
                        tag = uiState.value.lastControl.uidOfTheObject,
                        imageUri = if (uiState.value.imageUri.path != null) uiState.value.imageUri.path.toString() else "dfa",
                        description = uiState.value.incidentDescription
                    ))
            } else {
                val currentTime = SimpleDateFormat("HH:mm:ss dd.MM.yy", Locale.getDefault()).format(Date())
                reportRepository.saveReport(
                    Report(
                        time = currentTime,
                        guardID = uiState.value.uidOfGuard,
                        latitude = uiState.value.lastControl.latitude,
                        longitude = uiState.value.lastControl.longitude ,
                        tag = null,
                        imageUri = if (uiState.value.imageUri.path != null) uiState.value.imageUri.path.toString() else "dfa",
                        description = uiState.value.incidentDescription
                    ))

            }

        }
    }
    fun getReports(): Flow<List<Report>> {
        return reportRepository.getReports()
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as HitMonitoringApplication)
                val tagInfoRepository = application.container.tagInfoRepository
                val checkInfoRepository = application.container.checkInfoRepository
                val locationRepository = application.container.locationRepository
                val reportRepository = application.container.reportInfoRepository

                AppViewModel(tagInfoRepository = tagInfoRepository as NetworkTagInfoRepository,
                    checkInfoRepository = checkInfoRepository as CheckInfoRepositoryImpl,
                    locationRepository = locationRepository,
                    reportRepository = reportRepository as ReportInfoRepositoryImpl

                )
            }
        }
    }

}