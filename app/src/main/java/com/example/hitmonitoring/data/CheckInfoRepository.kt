package com.example.hitmonitoring.data

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.hitmonitoring.database.AppDatabase
import com.example.hitmonitoring.database.DatabaseProvider
import com.example.hitmonitoring.database.Entities.Checks
import com.example.hitmonitoring.network.HitMonitoringApiService
import com.example.hitmonitoring.ui.data.Control
import com.example.hitmonitoring.ui.data.NFCTag
import kotlinx.coroutines.flow.Flow

interface CheckInfoRepository {
    fun getControlsInfo(): Flow<List<Checks>>
    suspend fun saveControl(control: Control, nameOfTheGuard: String)
}

class CheckInfoRepositoryImpl(private val context: Context,
                              private val tagApiService : HitMonitoringApiService): CheckInfoRepository {


    override  fun getControlsInfo(): Flow<List<Checks>> {
        return DatabaseProvider.getDatabase(context).checkDao().getAll()
    }

    override suspend fun saveControl(control: Control,nameOfTheGuard : String) {
        try {
            throw Exception()
        } catch (e: Exception) {
            val appDatabase = DatabaseProvider.getDatabase(context)
            appDatabase.checkDao().insertAll(Checks(
                time = control.timeOfControl,
                guardID = nameOfTheGuard,
                latitude = control.latitude,
                longitude = control.longitude,
                tagId = control.uidOfTheObject,
                tagName = control.nameOfTheObject
                ))
        }
    }

}