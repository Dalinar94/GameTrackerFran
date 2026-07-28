package com.fran.gametrackerdefran.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.fran.gametrackerdefran.ui.components.AppTopBar
import com.fran.gametrackerdefran.ui.components.WishlistCard
import com.fran.gametrackerdefran.ui.navigation.Screen
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel

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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddWishlistGame.route)                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir juego"
                )
            }
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

                LazyColumn {
                    items(wishlistGames) { game ->
                        WishlistCard(
                            game = game,
                            onDelete = {
                                gameViewModel.deleteWishlistGame(game)
                            },
                            onMoveToLibrary = {
                                gameViewModel.moveWishlistGameToLibrary(game)
                            }
                        )
                    }
                }
            }
        }
    }
}