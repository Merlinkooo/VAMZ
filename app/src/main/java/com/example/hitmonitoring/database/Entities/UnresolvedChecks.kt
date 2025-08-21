package com.example.hitmonitoring.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "unresolved_checks")
data class UnresolvedChecks(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val time: LocalDateTime,
    @ColumnInfo(name = "guard_id") val guardID : String,
    val latitude: Double,
    val longitude: Double,
    val tag: String?,
)
