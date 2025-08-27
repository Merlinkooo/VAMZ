package com.example.hitmonitoring.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hitmonitoring.database.Entities.Checks
import com.example.hitmonitoring.database.Entities.Report
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDao {
    @Query("SELECT * FROM reports")
    fun getAll(): Flow<List<Report>>


    @Insert
    fun insertAll(vararg users: Report)

    @Delete
    fun delete(user: Report)
}