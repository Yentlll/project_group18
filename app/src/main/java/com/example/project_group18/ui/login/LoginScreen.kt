package com.example.project_group18.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project_group18.R
import com.example.project_group18.ui.theme.Projectgroup18Theme
import com.example.project_group18.ui.theme.TwatterBlue
import com.example.project_group18.ui.theme.TwatterWhite

@Composable
fun LoginScreen(loginViewModel: LoginScreenViewModel = viewModel()) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        loginViewModel.checkRootStatus(context)
    }


    if (loginViewModel.popupMessage != null) {
        AlertDialog(
            onDismissRequest = { loginViewModel.dismissDialog() },
            confirmButton = {
                TextButton(onClick = { loginViewModel.dismissDialog() }) {
                    Text("OK")
                }
            },
            title = { Text("Notification") },
            text = { Text(loginViewModel.popupMessage ?: "") }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(15.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_app_monochrome),
            contentDescription = "App Logo Twatter",
            modifier = Modifier.size(300.dp)
        )

        Spacer(Modifier.height(30.dp))

        Text(text = "Login to your account")

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    loginViewModel.login(username, password)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = TwatterBlue, contentColor = TwatterWhite),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.height(50.dp).width(150.dp)
        ) {
            Text(text = "Login", fontSize = 20.sp)
        }

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    loginViewModel.registerUser(username, password)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = TwatterBlue, contentColor = TwatterWhite),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.height(50.dp).width(150.dp)
        ) {
            Text(text = "Register", fontSize = 20.sp)
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