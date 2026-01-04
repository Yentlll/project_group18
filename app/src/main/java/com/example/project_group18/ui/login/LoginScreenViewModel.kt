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

    var loggedInUser by mutableStateOf<String?>(null)

    fun registerUser(user: String, pass: String) {
        val encodedPassword = Base64.encodeToString(pass.toByteArray(), Base64.NO_WRAP)
        viewModelScope.launch(Dispatchers.IO) {
            userDao.registerUser(User(username = user, password = encodedPassword))
        }
    }

    fun login(user: String, pass: String) {
        val encodedPassword = Base64.encodeToString(pass.toByteArray(), Base64.NO_WRAP)
        viewModelScope.launch(Dispatchers.IO) {
            val foundUser = userDao.loginUser(user, encodedPassword)
            if (foundUser != null) {
                loggedInUser = foundUser.username
            }
        }
    }
}