package com.example.project_group18.ui.login

import androidx.lifecycle.ViewModel
import com.example.project_group18.MainApplication
import com.example.project_group18.User

class LoginScreenViewModel : ViewModel() {

    val userDao = MainApplication.userDatabase.getUserDao()

    fun registerUser() {
        userDao.registerUser(User(username = "yentl", password = "password"))
    }

}