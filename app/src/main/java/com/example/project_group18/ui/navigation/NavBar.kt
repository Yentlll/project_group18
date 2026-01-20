package com.example.project_group18.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.project_group18.R
import com.example.project_group18.ui.login.LoginScreen
import com.example.project_group18.ui.theme.Projectgroup18Theme
import com.example.project_group18.ui.timeline.TimelineScreen
import com.example.project_group18.ui.users.UserScreen


enum class Destination(
    val route: String,
    val label: String,
    val iconRes: Int,
    val contentDescription: String
) {
    LOGIN("Login Screen","Login Screen", R.drawable.login, "login"),
    TIMELINE("Timeline","Timeline", R.drawable.timeline, "timeline"),
    USERS("Users","Users", R.drawable.settings, "users")
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
                    Destination.LOGIN -> LoginScreen()
                    Destination.TIMELINE -> TimelineScreen()
                    Destination.USERS -> UserScreen()
                }
            }
        }
    }
}

@Composable
fun AppScaffold(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val startDestination = Destination.LOGIN
    var selectedDestination by rememberSaveable { mutableStateOf(startDestination) }
    //rememberSaveable used, because remember even if screen rotates

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEach { destination ->
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
                                painter = painterResource(id = destination.iconRes),
                                contentDescription = destination.contentDescription,
                                modifier = Modifier.size(24.dp)
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
fun LoginPreview() {
    Projectgroup18Theme {
        AppScaffold()
    }
}

