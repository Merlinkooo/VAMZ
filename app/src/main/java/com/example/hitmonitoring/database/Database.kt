package com.example.hitmonitoring.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hitmonitoring.database.Entities.Check
import com.example.hitmonitoring.database.Entities.Report
import com.example.hitmonitoring.database.Entities.User
import com.example.hitmonitoring.database.dao.CheckDao
import com.example.hitmonitoring.database.dao.ReportDao
import com.example.hitmonitoring.database.dao.UserDao


@Database(entities = [User::class, Check::class, Report::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun checkDao(): CheckDao
    abstract fun reportDao(): ReportDao
}