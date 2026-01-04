package com.example.project_group18.ui.login

import android.util.Base64
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_group18.MainApplication
import com.example.project_group18.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

    private val userDao = MainApplication.userDatabase.getUserDao()

    var popupMessage by mutableStateOf<String?>(null)

    fun registerUser(user: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingUser = userDao.getUserByUsername(user)

            if (existingUser != null) {
                popupMessage = "Registration failed: User already exists!"
            } else {
                val encodedPassword = Base64.encodeToString(pass.toByteArray(), Base64.NO_WRAP)
                userDao.registerUser(User(username = user, password = encodedPassword))
                popupMessage = "Registration successful!"
            }
        }
    }

    fun login(user: String, pass: String) {
        val encodedPassword = Base64.encodeToString(pass.toByteArray(), Base64.NO_WRAP)
        viewModelScope.launch(Dispatchers.IO) {
            val foundUser = userDao.loginUser(user, encodedPassword)
            if (foundUser != null) {
                popupMessage = "Successfully logged in as ${foundUser.username}!"
            } else {
                popupMessage = "Login failed: Wrong username or password."
            }
        }
    }

    fun dismissDialog() {
        popupMessage = null
    }
}