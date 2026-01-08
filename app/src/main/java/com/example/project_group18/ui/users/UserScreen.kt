package com.example.project_group18.ui.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project_group18.ui.theme.Projectgroup18Theme

const val showSecrets = false
@Composable
fun UserScreen(viewModel: UserViewModel = viewModel() ) {
    val context = LocalContext.current
    val user by viewModel.user.collectAsState()
    var idText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(160.dp))
        Button(onClick = { viewModel.loadUser() }) {
            Text("Load a random Twatter user")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (user == null) {
            Text("Loading user...")
            return@Column
        }

        if (!showSecrets) {
            Text("ID: ${user!!.id}")
            Text("Name: ${user!!.name}")
            Text("Username: ${user!!.username}")
            Text("Email: ${user!!.email}")
        } else {
            Text("ID: ${user!!.id}")
            Text("Name: ${user!!.name}")
            Text("Username: ${user!!.username}")
            Text("Email: ${user!!.email}")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Secret Info!! Now you can Dox this person :)")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Phone: ${user!!.phone}")
            Text("Website: ${user!!.website}")
            Text("Company Name: ${user!!.company.name}")
            Text("Catchphrase: ${user!!.company.catchPhrase}")
            Text("BS: ${user!!.company.bs}")
            Text("Street: ${user!!.address.street}")
            Text("Suite: ${user!!.address.suite}")
            Text("City: ${user!!.address.city}")
            Text("Zipcode: ${user!!.address.zipcode}")
            Text("Lat: ${user!!.address.geo.lat}")
            Text("Lng: ${user!!.address.geo.lng}")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "give user id you want to encrypt")
        OutlinedTextField(
            value = idText,
            onValueChange = { idText = it },
            label = { Text(text = "Id user") },
            singleLine =  true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )
        Button(onClick = {
            if (idText.toInt() in 1..10){
                viewModel.encryptUserFile(idText.toInt(), context)
            }
        }) {
            Text("Encrypt user data in a file")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Projectgroup18Theme {
        UserScreen()
    }
}