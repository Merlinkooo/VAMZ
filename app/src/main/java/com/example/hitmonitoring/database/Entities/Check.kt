package com.example.hitmonitoring.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Check(
    @PrimaryKey(autoGenerate = true) val tagId: String="",
    val time: String,
    @ColumnInfo(name = "guard_id") val guardID : String="",
    val latitude: Double?,
    val longitude: Double?,
    val tag: String,
)

