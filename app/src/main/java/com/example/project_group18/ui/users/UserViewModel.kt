package com.example.project_group18.ui.users

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_group18.network.RetrofitClientUser
import com.example.project_group18.security.EncryptionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.ThreadLocalRandom

class UserViewModel : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _encryptionSuccess = MutableStateFlow(false)
    val encryptionSuccess: StateFlow<Boolean> = _encryptionSuccess

    private val _encryptionAttempted = MutableStateFlow(false)
    val encryptionAttempted: StateFlow<Boolean> = _encryptionAttempted

    fun loadUser() {
        val randomInteger = ThreadLocalRandom.current().nextInt(1, 10)
        viewModelScope.launch {
            _user.value = RetrofitClientUser.api.getUserById(randomInteger)
        }
        resetEncryptionSuccess()
        _encryptionAttempted.value = false
    }

    fun encryptUserFile(user: User, context: Context) {
        //Dispatchers.IO keeps main thread free, avoid app not responding
        viewModelScope.launch(Dispatchers.IO) {
            _encryptionAttempted.value = true

            val fileName = "user_data_${user.id}.txt"
            val userData = """
                ID: ${user.id}
                Name: ${user.name}
                Username: ${user.username}
                Email: ${user.email}
            """.trimIndent()

            val file = File(context.filesDir, fileName)
            file.writeText(userData)

            val encryptedFileName = "encrypted-$fileName"
            val encryptionManager = EncryptionManager(context)
            _encryptionSuccess.value = encryptionManager.encryptFile(file, encryptedFileName)
        }
    }

    fun resetEncryptionSuccess() {
        _encryptionSuccess.value = false
    }

}



