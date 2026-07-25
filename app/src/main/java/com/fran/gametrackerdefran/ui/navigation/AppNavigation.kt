package com.fran.gametrackerdefran.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fran.gametrackerdefran.ui.screens.AddGameScreen
import com.fran.gametrackerdefran.ui.screens.EditGameScreen
import com.fran.gametrackerdefran.ui.screens.HomeScreen
import com.fran.gametrackerdefran.ui.screens.StatisticsScreen

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

        composable(Screen.Statistics.route) {
            StatisticsScreen()
        }
        composable(
            route = Screen.EditGame.route,
            arguments = listOf(
                navArgument("gameId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val gameId =
                backStackEntry.arguments?.getInt("gameId") ?: 0

            EditGameScreen(
                navController = navController,
                gameId = gameId
            )

        }

    }

}