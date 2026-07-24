package com.fran.gametrackerdefran.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fran.gametrackerdefran.ui.components.AppTopBar
import com.fran.gametrackerdefran.ui.components.GameCard
import com.fran.gametrackerdefran.ui.navigation.Screen
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val gameViewModel: GameViewModel = viewModel()
    val juegos = gameViewModel.games

    Scaffold(

        topBar = {
            AppTopBar(
                title = "GameTracker"
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddGame.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir juego"
                )
            }
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {

            items(juegos) { juego ->

                GameCard(
                    game = juego,
                    onClick = {
                        navController.navigate(
                            Screen.EditGame.createRoute(juego.id)
                        )
                    }
                )

            }

        }

    }

}
