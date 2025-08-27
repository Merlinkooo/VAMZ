package com.example.hitmonitoring.database.Entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

import java.time.LocalDateTime

@Parcelize
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
) : Parcelable


