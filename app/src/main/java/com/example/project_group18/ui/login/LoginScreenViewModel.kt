package com.example.project_group18.ui.login

import android.util.Base64 // ADD THIS IMPORT
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_group18.MainApplication
import com.example.project_group18.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

    private val userDao = MainApplication.userDatabase.getUserDao()

    fun registerUser(user: String, pass: String) {
        // 1. Encode the password to Base64 right here
        val encodedPassword = Base64.encodeToString(pass.toByteArray(), Base64.NO_WRAP)

        // 2. Save the User object with the ENCODED password
        viewModelScope.launch(Dispatchers.IO) {
            userDao.registerUser(User(username = user, password = encodedPassword))

            println("User $user registered with encoded password: $encodedPassword")
        }
    }
}