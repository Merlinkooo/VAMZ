package com.example.hitmonitoring.data

import android.content.Context
import com.example.hitmonitoring.network.HitMonitoringApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val checkInfoRepository: CheckInfoRepository
    val tagInfoRepository: TagInfoRepository
    val locationRepository : LocationRepository
}

class DefaultAppContainer(context: Context) : AppContainer {

    private  val baseUrl = "https://hitmonitoring.sk"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: HitMonitoringApiService by lazy {
        retrofit.create(HitMonitoringApiService::class.java)
    }

    override val checkInfoRepository: CheckInfoRepository by lazy {
        CheckInfoRepositoryImpl(context,retrofitService)
    }
    override val tagInfoRepository: TagInfoRepository by lazy {
        NetworkTagInfoRepository(retrofitService)
    }

    override val locationRepository: LocationRepository by lazy {
        LocationRepository(context)
    }


}