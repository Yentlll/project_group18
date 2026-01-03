package com.example.project_group18.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_group18.MainApplication
import com.example.project_group18.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

    private val userDao = MainApplication.userDatabase.getUserDao()

    fun registerUser(user: String, pass: String) {
        // Use viewModelScope to run the DB insert on a background thread (IO)
        viewModelScope.launch(Dispatchers.IO) {
            userDao.registerUser(User(username = user, password = pass))
            // You could add a print here to verify in the console:
            println("User $user registered successfully!")
        }
    }
}