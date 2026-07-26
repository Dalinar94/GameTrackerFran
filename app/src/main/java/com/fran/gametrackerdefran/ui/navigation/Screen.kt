package com.fran.gametrackerdefran.ui.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object AddGame : Screen("addGame")

    object EditGame : Screen("editGame/{gameId}") {

        fun createRoute(gameId: Int): String {
            return "editGame/$gameId"
        }

    }

    object GameDetail : Screen("gameDetail/{gameId}") {

        fun createRoute(gameId: Int): String {
            return "gameDetail/$gameId"
        }

    }

    data object Statistics : Screen("statistics")
}