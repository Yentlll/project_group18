package com.example.project_group18.data

import android.content.Context
import android.util.Log
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import java.io.File

class SecureStorage(context: Context) {

    private val appContext = context.applicationContext

    private val masterKey: MasterKey by lazy {
        MasterKey.Builder(appContext)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    private fun getEncryptedFile(fileName: String): EncryptedFile {
        val file = File(appContext.filesDir, fileName)

        return EncryptedFile.Builder(
            appContext,
            file,
            masterKey,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()
    }

    fun encryptFile(inputFile: File, encryptedName: String) {
        val encryptedFile = getEncryptedFile(encryptedName)

        inputFile.inputStream().use { input ->
            encryptedFile.openFileOutput().use { output ->
                input.copyTo(output)
            }
        }

        Log.d("SecureStorage", "File encrypted: $encryptedName")
    }

    fun decryptFile(encryptedName: String): ByteArray {
        val encryptedFile = getEncryptedFile(encryptedName)

        return encryptedFile.openFileInput().use {
            it.readBytes()
        }
    }
}