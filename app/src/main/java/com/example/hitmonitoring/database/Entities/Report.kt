package com.example.hitmonitoring.database.Entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.time.LocalDateTime

data class Report(
    @PrimaryKey(autoGenerate = true) val id :Int=1,
    @ColumnInfo(name = "id") val time: LocalDateTime,
    @ColumnInfo(name = "guard_id") val guardID : Int,
    val latitude: Double,
    val longitude: Double,
    val tag: Int?,
)


