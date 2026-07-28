package com.fran.gametrackerdefran.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fran.gametrackerdefran.ui.screens.AddGameScreen
import com.fran.gametrackerdefran.ui.screens.AddWishlistGameScreen
import com.fran.gametrackerdefran.ui.screens.EditGameScreen
import com.fran.gametrackerdefran.ui.screens.GameDetailScreen
import com.fran.gametrackerdefran.ui.screens.HomeScreen
import com.fran.gametrackerdefran.ui.screens.SettingsScreen
import com.fran.gametrackerdefran.ui.screens.StatisticsScreen
import com.fran.gametrackerdefran.ui.screens.WishlistScreen
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel
@Composable
fun AppNavigation(gameViewModel: GameViewModel ) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                gameViewModel = gameViewModel
            )

        }

        composable(Screen.AddGame.route) {
            AddGameScreen(
                navController = navController,
                gameViewModel = gameViewModel
            )
        }

        composable(Screen.Statistics.route) {
            StatisticsScreen(
                navController = navController,
                gameViewModel = gameViewModel
            )
        }

        composable(
            route = Screen.GameDetail.route,
            arguments = listOf(
                navArgument("gameId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val gameId =
                backStackEntry.arguments?.getInt("gameId") ?: 0

            GameDetailScreen(
                gameId = gameId,
                gameViewModel = gameViewModel,
                onEdit = {
                    navController.navigate(
                        Screen.EditGame.createRoute(gameId)
                    )
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                navController = navController,
                gameViewModel = gameViewModel
            )
        }
        composable(Screen.Wishlist.route) {
            WishlistScreen(
                navController = navController,
                gameViewModel = gameViewModel
            )
        }
        composable(Screen.AddWishlistGame.route) {
            AddWishlistGameScreen(
                navController = navController,
                gameViewModel = gameViewModel
            )
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
                gameId = gameId,
                gameViewModel = gameViewModel
            )

        }

    }

}