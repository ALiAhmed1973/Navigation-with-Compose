package com.aliahmed1973.navigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aliahmed1973.navigationapp.ui.theme.NavigationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavApp()
        }
    }
}

@Composable
fun NavApp() {
    NavigationAppTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        Scaffold(bottomBar = {
            BottomNavigation() {
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Home, null) },
                    label = { Text(text = "Home") },
                    selected = currentDestination?.hierarchy?.any { it.route == Screen.Home.route } == true,
                    onClick = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(
                                navController.graph.findStartDestination().id
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Favorite, null) },
                    label = { Text(text = "Favorite") },
                    selected = currentDestination?.hierarchy?.any { it.route == Screen.Favorite.route } == true,
                    onClick = { navController.navigate(Screen.Favorite.route){
                        popUpTo(
                            navController.graph.findStartDestination().id
                        ) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    } })
            }
        }) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                Modifier.padding(it)
            )
            {
                composable(route = Screen.Home.route)
                {
                    HomeScreen()
                }
                composable(route = Screen.Favorite.route)
                {
                    FavoriteScreen()
                }
            }

        }
    }
}

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home", textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FavoriteScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Favorite", textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DetailsScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Details", textAlign = TextAlign.Center
        )
    }
}


sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object Favorite : Screen("Favorite")
    object Details : Screen("Details")

}



