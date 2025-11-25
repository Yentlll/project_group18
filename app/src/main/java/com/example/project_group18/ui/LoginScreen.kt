package com.example.project_group18.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.project_group18.R

@Composable
fun LoginScreen(user: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "App Logo Twatter",
            modifier = Modifier.size(135.dp)
        )

        Spacer(Modifier.height(24.dp))


    TimelineScreen(user = user)

    //Text(
        //    "Welcome to Twatter $user!",
        //    style = MaterialTheme.typography.headlineMedium,
        //    textAlign = TextAlign.Center
        //)
    }
}