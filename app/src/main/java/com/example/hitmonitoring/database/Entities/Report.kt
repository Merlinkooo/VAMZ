package com.example.hitmonitoring.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "reports")
data class Report(
    @PrimaryKey(autoGenerate = true) val id :Int=0,
    val time: String,
    @ColumnInfo(name = "guard_id") val guardID : String,
    val latitude: Double,
    val longitude: Double,
    val tag: String?,
    val imageUri: String,
    val description: String?
)


