package com.example.hitmonitoring.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hitmonitoring.database.Entities.Checks
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckDao {
    @Query("SELECT * FROM checks order by time DESC")
    fun getAll(): Flow<List<Checks>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg checks: Checks)

    @Delete
    suspend fun delete(check: Checks)
}