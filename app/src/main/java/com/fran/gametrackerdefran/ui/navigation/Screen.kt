package com.fran.gametrackerdefran.ui.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object AddGame : Screen("addGame")

    object EditGame : Screen("editGame/{gameId}") {

        fun createRoute(gameId: Int): String {
            return "editGame/$gameId"
        }

    }

}