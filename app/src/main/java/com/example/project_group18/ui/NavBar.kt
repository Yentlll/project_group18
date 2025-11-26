package com.example.project_group18.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector



@Composable
fun NavigationBar(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val startDestination = Destination.LOGIN
    var selectedDestination by rememberSaveable { mutableStateOf(startDestination) }
    //rememberSaveable used, because remember even if screen rotates

    Scaffold (
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, destination -> NavigationBarItem(
                    selected = selectedDestination == index,
                    onclick = {
                        navController.navigate((route = destination.route)
                        selectedDestination = index
                    },
                            icon = {
                                Icon(
                                    destination.icon,
                                    contentDescription = destination.contentDescription
                                )
                            },
                            label = { Text(destination.label)}
                )
                }
            }
        }
                ){contentPadding ->
                    AppNavHost(navController, startDestination, modifier = Modifier.padding(contentPadding))

    }
}
