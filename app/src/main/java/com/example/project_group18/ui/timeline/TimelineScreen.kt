package com.example.project_group18.ui.timeline

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class Tweet(
    val user: String,
    val content: String
)

@Composable
fun TimelineScreen() {

    //todo: maybe store tweets in database or ad fetch from an api???
    val tweets = listOf(
        Tweet("alice", "Welcome to Twatter!"),
        Tweet("bob", "I'm exploiting an IDOR right now."),
        Tweet("charlie", "Malware POST request sent successfully."),
        Tweet("you", "I just joined Twatter!!!"),
        Tweet("dave", "Just intercepted my own API traffic… why is debugging so fun?"),
        Tweet("eve", "If your password is 'password123', we need to talk."),
        Tweet("mallory", "Wrote a Frida script and broke my own app. 10/10 would do again."),
        Tweet("trent", "Room database is cool until you forget to write a DAO."),
        Tweet("peggy", "Accidentally granted my app CAMERA permission… ignore the noise."),
        Tweet("oscar", "Just decompiled Twatter. Why is everything so… vulnerable? 😂"),
        Tweet("victor", "SSL pinning bypassed. Time to feel powerful."),
        Tweet("wendy", "I swear my emulator lagged when I enabled root detection.")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(tweets) { tweet ->
            TweetItem(tweet)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TimelinePreview() {
    TimelineScreen()
}