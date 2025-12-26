package com.example.project_group18.ui.timeline

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_group18.network.RetrofitClient
import kotlinx.coroutines.launch

class TimelineViewModel : ViewModel() {

    var tweets = mutableStateOf<List<Tweet>>(emptyList())
        private set

    fun loadTweets() {
        viewModelScope.launch {
            try {
                tweets.value = RetrofitClient.api.getTweets(
                    "Bearer FAKE_TOKEN_PROJECT_GROUP18"
                )
            } catch (e: Exception) {
                tweets.value = emptyList() // safe fallback
            }
        }
    }

}