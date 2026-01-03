package com.example.project_group18.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project_group18.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        const val NAME = "User_DB"
    }

    abstract fun getUserDao() : UserDao

}