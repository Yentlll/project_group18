package com.example.project_group18.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project_group18.R
import com.example.project_group18.ui.timeline.TimelineScreen
import com.example.project_group18.ui.theme.Projectgroup18Theme


@Composable
fun LoginScreen(user: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(15.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "App Logo Twatter",
            modifier = Modifier.size(120.dp)
        )

        Spacer(Modifier.height(2.5.dp))


        TimelineScreen(user = user)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Projectgroup18Theme {
        LoginScreen("Testuser")
    }
}