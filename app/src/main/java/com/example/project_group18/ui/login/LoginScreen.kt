package com.example.project_group18.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project_group18.R
import com.example.project_group18.ui.timeline.TimelineScreen
import com.example.project_group18.ui.theme.Projectgroup18Theme


@Composable
fun LoginScreen() {

    //Variables for login
    var username by remember {
        mutableStateOf("") }
    var password by remember {
        mutableStateOf("") }

    //Logo twatter design
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

        Spacer(Modifier.height(150.dp))

        //Login form

        Text(text = "Login to your account")

        OutlinedTextField(value = username, onValueChange = {
            username = it
        }, label = {
            Text(text = "Username")
        })
        OutlinedTextField(value = password, onValueChange = {
            password = it
        }, label = {
            Text(text = "Password")
        })

        Spacer(Modifier.height(20.dp))

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Login")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Projectgroup18Theme {
        LoginScreen()
    }
}