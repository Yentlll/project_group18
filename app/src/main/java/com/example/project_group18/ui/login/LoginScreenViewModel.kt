package com.example.project_group18.ui.login

import android.content.Context
import android.util.Base64
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_group18.MainApplication
import com.example.project_group18.User
import com.scottyab.rootbeer.RootBeer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

    private val userDao = MainApplication.userDatabase.getUserDao()

    var popupMessage by mutableStateOf<String?>(null)

    var isRooted by mutableStateOf(false)
        private set


    fun checkRootStatus(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val rootBeer = RootBeer(context)
                val result = rootBeer.isRooted

                launch(Dispatchers.Main) {
                    isRooted = result
                }
            } catch (e: Exception) {
                e.printStackTrace()
                launch(Dispatchers.Main) {
                    isRooted = false
                }
            }
        }
    }


    fun registerUser(user: String, pass: String) {
        if (isRooted){
                popupMessage = "Registration failed: Device is rooted!"
                return
        }

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
        if (isRooted){
            popupMessage = "Login failed: Device is rooted!"
            return
        }

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