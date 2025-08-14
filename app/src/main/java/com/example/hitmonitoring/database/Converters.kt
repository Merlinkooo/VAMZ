package com.example.hitmonitoring.database

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long): LocalDateTime? {
        return value.let {
            Instant.ofEpochMilli(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
        }
    }

    @TypeConverter
    fun localDateTimeToTimestamp(dateTime: LocalDateTime?): Long? {
        return dateTime?.atZone(ZoneId.systemDefault())
            ?.toInstant()
            ?.toEpochMilli()
    }
}