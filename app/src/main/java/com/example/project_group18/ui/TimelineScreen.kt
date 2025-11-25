package com.example.project_group18.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


data class Tweet(
    val user: String,
    val content: String
)

@Composable
fun TimelineScreen(user: String) {


    val tweets = listOf(
        Tweet("alice", "Hello World!"),
        Tweet("bob", "This app is so cool!"),
        Tweet("charlie", "I installed malware :)"),
        Tweet("testuser", "I just joined Twatter!!"),
        Tweet("user1", "I am gonna exploit this app! :d")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(tweets) { tweet ->
            TweetItem(tweet)
        }
    }
}