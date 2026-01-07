package com.example.project_group18.ui.timeline

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel




@Composable
fun TimelineScreen(viewModel: TimelineViewModel = viewModel()) {

    LaunchedEffect(Unit) {
        viewModel.loadTweets()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(viewModel.tweets.value) { tweet ->
            TweetItem(tweet)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TimelinePreview() {
    TimelineScreen()
}