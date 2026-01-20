package com.example.project_group18.security

import android.content.Context
import android.util.Log
import com.example.project_group18.data.SecureStorage
import java.io.File

class EncryptionManager(private val context: Context) {

    fun encryptFile(file: File, encryptedFileName: String): Boolean {
        return try {
            val secureStorage = SecureStorage(context)
            val encryptedFile = File(context.filesDir, encryptedFileName)
            if (encryptedFile.exists()) {
                encryptedFile.delete()
            }
            secureStorage.encryptFile(file, encryptedFileName)
            file.delete()
            true

        } catch (e: Exception) {
            Log.e("EncryptionManager", "Encryption failed for ${file.name}", e)
            false
        }
    }
}