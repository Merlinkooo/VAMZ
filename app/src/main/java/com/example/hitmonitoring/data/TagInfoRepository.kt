package com.example.hitmonitoring.data


import com.example.hitmonitoring.network.HitMonitoringApiService
import com.example.hitmonitoring.ui.data.NFCTag
import retrofit2.Retrofit

interface TagInfoRepository {
    suspend fun getTagInfo(tagId: String): NFCTag
    suspend fun getConnectionStatus() : NFCTag
}

class NetworkTagInfoRepository(
    private val tagApiService : HitMonitoringApiService
): TagInfoRepository {
    override suspend fun getTagInfo(tagId: String): NFCTag {
        return tagApiService.getNfcTagInfo(tagId)
    }

    override suspend fun getConnectionStatus(): NFCTag {
        return tagApiService.getConnectionStatus()
    }

}