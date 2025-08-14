package com.example.hitmonitoring.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hitmonitoring.database.Entities.User

interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE id=(:tag)")
    fun findByTag(tag: String): User?

    @Insert
    fun insertAll(vararg users: User)



    @Delete
    fun delete(user: User)
}