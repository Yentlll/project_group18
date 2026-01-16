package com.example.project_group18.ui.users


import android.content.Context
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

    private val _encryptionSuccess = MutableStateFlow(false)
    val encryptionSuccess: StateFlow<Boolean> = _encryptionSuccess

    fun loadUser() {
        val randomInteger = ThreadLocalRandom.current().nextInt(1, 10)
        viewModelScope.launch {
            _user.value = RetrofitClientUser.api.getUserById(randomInteger)
        }
        resetEncryptionSuccess()
    }

    fun encryptUserFile(user: User, context: Context) {
        viewModelScope.launch {
            val fileName = "user_data_${user.name}.txt"

            val userData = """
            ID: ${user.id}
            Name: ${user.name}
            Username: ${user.username}
            Email: ${user.email}
            """.trimIndent()

            val file = File(context.filesDir, fileName)
            file.writeText(userData)

            val encryptedFileName = "encrypted-$fileName"
            val encryptedFile = File(context.filesDir, encryptedFileName)

            if (encryptedFile.exists()) {
                encryptedFile.delete()
            }

            try {
                val secureStorage = SecureStorage(context)
                secureStorage.encryptFile(file, encryptedFileName)
                _encryptionSuccess.value = true
            } finally {
                if (file.exists()) file.delete()
            }
        }
    }

    fun resetEncryptionSuccess() {
        _encryptionSuccess.value = false
    }

}



