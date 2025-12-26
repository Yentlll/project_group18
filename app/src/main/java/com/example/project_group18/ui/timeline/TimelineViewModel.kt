package com.example.project_group18.ui.timeline

import android.util.Log
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
                val response = RetrofitClient.api.getTweets(
                    "https://gitlab.ti.howest.be/aykan.ridvanov/api_project_mobile/-/raw/main/db.json?ref_type=heads"
                )
                Log.d("TimelineViewModel", "Response tweets size: ${response.tweets.size}")
                tweets.value = response.tweets
            } catch (e: Exception) {
                Log.e("TimelineViewModel", "Error fetching tweets", e)
                tweets.value = emptyList()
            }
        }
    }

}