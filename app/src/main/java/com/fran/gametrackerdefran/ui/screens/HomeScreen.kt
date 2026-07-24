package com.fran.gametrackerdefran.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fran.gametrackerdefran.data.Game
import com.fran.gametrackerdefran.ui.components.GameCard
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.navigation.NavController
import com.fran.gametrackerdefran.ui.navigation.Screen
import com.fran.gametrackerdefran.ui.components.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val juegos = listOf(

        Game(
            1,
            "Elden Ring",
            "PS5",
            120,
            5,
            "Obra maestra",
            "Completado"
        ),

        Game(
            2,
            "Cyberpunk 2077",
            "PC",
            35,
            4,
            "Muy divertido",
            "Jugando"
        )

    )

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

                GameCard(game = juego)

            }

        }

    }

}