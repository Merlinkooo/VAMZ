package com.example.hitmonitoring.data

import com.example.hitmonitoring.database.Entities.Checks
import com.example.hitmonitoring.database.Entities.Report
import com.example.hitmonitoring.database.dao.ReportDao
import kotlinx.coroutines.flow.Flow


interface ReportInfoRepository {
    suspend fun saveReport(report : Report)
     fun getReports(): Flow<List<Report>>
}

class ReportInfoRepositoryImpl(val reportDao: ReportDao): ReportInfoRepository {
    override suspend fun saveReport(report: Report) {
        reportDao.insertAll(report)
    }

    override fun getReports(): Flow<List<Report>> {
        return reportDao.getAll()
    }
}