package com.example.hitmonitoring.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hitmonitoring.database.Entities.Checks
import com.example.hitmonitoring.database.Entities.Report
import com.example.hitmonitoring.database.Entities.User
import com.example.hitmonitoring.database.dao.CheckDao
import com.example.hitmonitoring.database.dao.ReportDao
import com.example.hitmonitoring.database.dao.UserDao


@Database(entities = [User::class, Checks::class, Report::class], version = 4)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun checkDao(): CheckDao
    abstract fun reportDao(): ReportDao
}

object DatabaseProvider {
    @Volatile private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "database"
                    ).fallbackToDestructiveMigration(false).
            build()



            INSTANCE = instance
            instance
        }
    }
}