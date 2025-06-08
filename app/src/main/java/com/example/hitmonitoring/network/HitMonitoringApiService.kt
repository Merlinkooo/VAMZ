package com.example.hitmonitoring.network

import com.example.hitmonitoring.data.NFCTag
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://hitmonitoring.sk"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()


interface HitMonitoringApiService{
    @GET("testApi.php")
    suspend fun getNfcTagInfo(@Query("q") tagId: String) : NFCTag

    @GET("testApi.php")
    suspend fun getConnectionStatus(@Query("q") tagId: String="status") : NFCTag
}

object HitMonitoringApi {
    val retrofitService: HitMonitoringApiService by lazy {
        retrofit.create(HitMonitoringApiService::class.java)
    }
}