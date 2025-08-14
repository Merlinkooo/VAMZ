package com.example.hitmonitoring.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hitmonitoring.database.Entities.Check


interface CheckDao {
    @Query("SELECT * FROM `check`")
    fun getAll(): List<Check>


    @Insert
    fun insertAll(vararg checks: Check)

    @Delete
    fun delete(check: Check)
}