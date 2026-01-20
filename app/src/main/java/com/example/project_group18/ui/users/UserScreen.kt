package com.example.project_group18.ui.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project_group18.ui.theme.Projectgroup18Theme

const val showSecrets = false
@Composable
fun UserScreen(viewModel: UserViewModel = viewModel() ) {
    val context = LocalContext.current
    val user by viewModel.user.collectAsState()

    val encryptionSuccess by viewModel.encryptionSuccess.collectAsState()
    val encryptionAttempted by viewModel.encryptionAttempted.collectAsState()

    val currentUser = user

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(160.dp))
        Button(onClick = { viewModel.loadUser() }) {
            Text("Load a random Twatter user")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (currentUser == null) {
            Text("Loading user...")
            return@Column
        }

        if (!showSecrets) {
            Text("ID: ${currentUser.id}")
            Text("Name: ${currentUser.name}")
            Text("Username: ${currentUser.username}")
            Text("Email: ${currentUser.email}")
        } else {
            Text("ID: ${currentUser.id}")
            Text("Name: ${currentUser.name}")
            Text("Username: ${currentUser.username}")
            Text("Email: ${currentUser.email}")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Secret Info!! Now you can Dox this person :)")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Phone: ${currentUser.phone}")
            Text("Website: ${currentUser.website}")
            Text("Company Name: ${currentUser.company.name}")
            Text("Catchphrase: ${currentUser.company.catchPhrase}")
            Text("BS: ${currentUser.company.bs}")
            Text("Street: ${currentUser.address.street}")
            Text("Suite: ${currentUser.address.suite}")
            Text("City: ${currentUser.address.city}")
            Text("Zipcode: ${currentUser.address.zipcode}")
            Text("Lat: ${currentUser.address.geo.lat}")
            Text("Lng: ${currentUser.address.geo.lng}")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.encryptUserFile(currentUser, context)
             }) {
            Text("Encrypt basic user data in a file")
        }
        if(encryptionAttempted){
            if (encryptionSuccess) {
                Text("Encryption successful!"
                )
            }else{
                Text("Encryption failed")
            }
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