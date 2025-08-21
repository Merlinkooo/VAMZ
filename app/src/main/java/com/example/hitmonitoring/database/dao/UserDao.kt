package com.example.hitmonitoring.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hitmonitoring.database.Entities.User
@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM users WHERE id=(:tag)")
    fun findByTag(tag: String): User?

    @Insert
    fun insertAll(vararg users: User)



    @Delete
    fun delete(user: User)
}