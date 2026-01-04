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
        // ENCODE PASSWORD
        val encodedPassword = Base64.encodeToString(pass.toByteArray(), Base64.NO_WRAP)


        viewModelScope.launch(Dispatchers.IO) {
            userDao.registerUser(User(username = user, password = encodedPassword))
        }
    }
}