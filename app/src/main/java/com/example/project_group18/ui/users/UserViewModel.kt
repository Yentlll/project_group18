package com.example.project_group18.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_group18.network.RetrofitClientUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.ThreadLocalRandom

class UserViewModel : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun loadUser() {
        val randomInteger = ThreadLocalRandom.current().nextInt(1, 10)
        viewModelScope.launch {
            _user.value = RetrofitClientUser.api.getUserById(randomInteger)
        }
        }
    }