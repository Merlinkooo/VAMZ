package com.example.hitmonitoring.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hitmonitoring.database.Entities.UnresolvedChecks
import com.example.hitmonitoring.database.Entities.User

interface UnresolvedCheckDao {

        @Query("SELECT * FROM unresolved_checks")
        fun getAll(): List<UnresolvedChecks>

        @Insert
        fun insertAll(vararg users: User)

        @Delete
        fun delete(user: User)

}