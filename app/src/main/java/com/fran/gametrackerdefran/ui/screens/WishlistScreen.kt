package com.fran.gametrackerdefran.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.fran.gametrackerdefran.ui.components.AppTopBar
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
@Composable
fun WishlistScreen(
    navController: NavController,
    gameViewModel: GameViewModel
) {
    val wishlistGames by gameViewModel
        .allWishlistGames
        .collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Lista de deseos",
                showBackButton = true,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (wishlistGames.isEmpty()) {

                Text("Todavía no tienes juegos en tu lista de deseos.")

            } else {

                Text("Tienes ${wishlistGames.size} juegos en tu lista de deseos")

            }        }
    }
}