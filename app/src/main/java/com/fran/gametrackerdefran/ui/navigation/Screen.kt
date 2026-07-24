package com.fran.gametrackerdefran.ui.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object AddGame : Screen("add_game")

}