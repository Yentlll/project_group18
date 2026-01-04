package com.example.project_group18.ui.root

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.scottyab.rootbeer.RootBeer

class RootDetectViewModel : ViewModel(){
    var isRooted by mutableStateOf(false)
        private set

    fun checkDeviceRoot(context: Context){
        val rootBeer = RootBeer(context)
        isRooted = rootBeer.isRooted
    }
}