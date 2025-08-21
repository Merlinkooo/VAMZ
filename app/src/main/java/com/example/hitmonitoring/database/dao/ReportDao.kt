package com.example.hitmonitoring.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hitmonitoring.database.Entities.Report
@Dao
interface ReportDao {
    @Query("SELECT * FROM reports")
    fun getAll(): List<Report>


    @Insert
    fun insertAll(vararg users: Report)

    @Delete
    fun delete(user: Report)
}