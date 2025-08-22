package com.example.hitmonitoring.data

import com.example.hitmonitoring.database.dao.ReportDao


interface ReportInfoRepository {
    suspend fun saveReport()
}

class ReportInfoRepositoryImpl(val reportDao: ReportDao): ReportInfoRepository {
    override suspend fun saveReport() {
        reportDao.insertAll()
    }
}