package com.example.project_group18.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreens(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Login Screen")
    }
}

@Composable
fun TimelineScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Timeline")
    }
}

@Composable
fun SettingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Settings")
    }
}

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    LOGIN("Login Screen","Login Screen", Icons.Default.AccountCircle, "login"),
    TIMELINE("Timeline","Timeline", Icons.Default.Home, "timeline"),
    SETTINGS("Settings","Settings", Icons.Default.Settings, "settings")
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(navController,
        startDestination = startDestination.route
    ){
        Destination.entries.forEach{ destination ->
            composable(destination.route){
                when (destination){
                    Destination.LOGIN -> LoginScreens()
                    Destination.TIMELINE -> TimelineScreen()
                    Destination.SETTINGS -> SettingScreen()
                }
            }
        }
    }
}


@Composable
fun NavigationBar(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val startDestination = Destination.LOGIN
    var selectedDestination by rememberSaveable { mutableStateOf(startDestination) }
    //rememberSaveable used, because remember even if screen rotates

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == destination,
                        onClick = {
                            navController.navigate(route = destination.route){
                                launchSingleTop = true
                                restoreState = true
                            }
                            selectedDestination = destination
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { contentPadding ->
        AppNavHost(navController, startDestination, modifier = Modifier.padding(contentPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NavigationBar()
}