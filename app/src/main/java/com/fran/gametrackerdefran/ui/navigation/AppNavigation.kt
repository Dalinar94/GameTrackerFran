package com.fran.gametrackerdefran.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fran.gametrackerdefran.ui.screens.AddGameScreen
import com.fran.gametrackerdefran.ui.screens.HomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {

            HomeScreen(navController)

        }

        composable(Screen.AddGame.route) {

            AddGameScreen(navController)

        }

    }

}