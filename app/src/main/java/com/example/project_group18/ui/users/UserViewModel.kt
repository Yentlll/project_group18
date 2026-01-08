package com.example.project_group18.ui.users


import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_group18.network.RetrofitClientUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.ThreadLocalRandom

import com.example.project_group18.data.SecureStorage
class UserViewModel : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun loadUser() {
        val randomInteger = ThreadLocalRandom.current().nextInt(1, 10)
        viewModelScope.launch {
            _user.value = RetrofitClientUser.api.getUserById(randomInteger)
        }
    }

    fun encryptUserFile(id: Int, context: Context, fileName: String = "user_data_$id.txt") {
        viewModelScope.launch {
            val fetchedUser = RetrofitClientUser.api.getUserById(id)

            val userData = """
            ID: ${fetchedUser.id}
            Name: ${fetchedUser.name}
            Username: ${fetchedUser.username}
            Email: ${fetchedUser.email}
            """.trimIndent()

            val file = File(context.filesDir, fileName)
            file.writeText(userData)

            val secureStorage = SecureStorage(context)
            secureStorage.encryptFile(file,"encrypted-user_data_$id.txt")

            file.delete()

        }
    }
}



