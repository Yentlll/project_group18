package com.example.project_group18

import android.app.Application
import androidx.room.Room
import com.example.project_group18.data.UserDatabase

class MainApplication : Application() {

    companion object {

        lateinit var userDatabase: UserDatabase
    }

    override fun onCreate() {
        super.onCreate()
        userDatabase = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            UserDatabase.NAME
        ).build()
    }
}