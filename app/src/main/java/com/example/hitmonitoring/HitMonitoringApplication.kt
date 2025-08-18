package com.example.hitmonitoring

import android.app.Application
import com.example.hitmonitoring.data.AppContainer
import com.example.hitmonitoring.data.DefaultAppContainer

class HitMonitoringApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}