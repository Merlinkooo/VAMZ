package com.example.hitmonitoring.data

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.hitmonitoring.database.AppDatabase
import com.example.hitmonitoring.database.DatabaseProvider
import com.example.hitmonitoring.database.Entities.Check
import com.example.hitmonitoring.network.HitMonitoringApiService
import com.example.hitmonitoring.ui.data.Control
import com.example.hitmonitoring.ui.data.NFCTag

interface CheckInfoRepository {
    suspend fun getControlsInfo(tagId: String): List<Check>
    suspend fun saveControl(control: Control, nameOfTheGuard: String)
}

class CheckInfoRepositoryImpl(private val context: Context,
                              private val tagApiService : HitMonitoringApiService): CheckInfoRepository {


    override suspend fun getControlsInfo(tagId: String): List<Check> {
        return DatabaseProvider.getDatabase(context).checkDao().getAll()
    }

    override suspend fun saveControl(control: Control,nameOfTheGuard : String) {
        try {
            throw Exception()
        } catch (e: Exception) {
            val appDatabase = DatabaseProvider.getDatabase(context)
            appDatabase.checkDao().insertAll(Check(tagId = control.uidOfTheObject,
                time = control.timeOfControl,
                guardID = nameOfTheGuard,
                latitude = control.latitude,
                longitude = control.longitude,
                tag = control.nameOfTheObject))
        }
    }

}