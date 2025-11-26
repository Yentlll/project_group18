package com.example.project_group18.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): Flow<User>

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): Flow<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}