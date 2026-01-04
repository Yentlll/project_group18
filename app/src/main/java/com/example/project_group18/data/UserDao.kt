package com.example.project_group18.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.project_group18.User

@Dao
interface UserDao {

    @Insert
    suspend fun registerUser(user: User)

    @Query("SELECT * FROM User WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM User WHERE username = :user AND password = :pass LIMIT 1")
    suspend fun loginUser(user: String, pass: String): User?
}