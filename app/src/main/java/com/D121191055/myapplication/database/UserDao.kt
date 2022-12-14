package com.D121191055.myapplication.database

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(note: user)

    @Query("SELECT * FROM user ORDER BY id DESC")
    suspend fun getAllUser() : List<user>

    @Update
    suspend fun updateUser(note: user)

    @Delete
    suspend fun deleteUser(note: user)
}