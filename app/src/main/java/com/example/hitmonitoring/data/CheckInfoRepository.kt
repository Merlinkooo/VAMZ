package com.example.hitmonitoring.data

import androidx.compose.ui.platform.LocalContext
import com.example.hitmonitoring.database.AppDatabase
import com.example.hitmonitoring.database.DatabaseProvider
import com.example.hitmonitoring.ui.data.Control
import com.example.hitmonitoring.ui.data.NFCTag

interface CheckInfoRepository {
    suspend fun getControlsInfo(tagId: String): List<Control>
    suspend fun saveControl(control: Control)
}

class CheckInfoRepositoryImpl(): CheckInfoRepository {
    override suspend fun getControlsInfo(tagId: String): List<Control> {
        TODO("Not yet implemented")
    }

    override suspend fun saveControl(control: Control) {
        TODO("Not yet implemented")
    }

}