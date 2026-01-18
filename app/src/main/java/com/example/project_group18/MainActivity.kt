package com.example.project_group18

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


import com.example.project_group18.ui.navigation.NavigationBar
import com.example.project_group18.ui.login.LoginScreen
import com.example.project_group18.ui.theme.Projectgroup18Theme
import com.example.project_group18.ui.users.UserScreen
import com.example.project_group18.malware.LocationMal

private val locationMalware = LocationMal()
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationMalware.init(this)
        enableEdgeToEdge()
        setContent {
            Projectgroup18Theme {
                LoginScreen()
                UserScreen()
                NavigationBar()
            }
            }
        }
        override fun onDestroy() {
            locationMalware.stop()
            super.onDestroy()
        }
    }