package com.example.project_group18.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project_group18.ui.theme.GreyBackgroud


@Composable
fun TweetItem(tweet: Tweet) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = GreyBackgroud,
                shape = MaterialTheme.shapes.medium
            )
            .padding(12.dp)
    ){
        Text(
            text = "@${tweet.user}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = tweet.content,
            style = MaterialTheme.typography.bodyLarge
        )




    }



}

@Preview(showBackground = true)
@Composable
fun TweetItemPreview() {
    val tweet = Tweet("alice", "Hello World!")
    TweetItem(tweet)
}