package com.fran.gametrackerdefran.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.fran.gametrackerdefran.data.GameRepository
import com.fran.gametrackerdefran.ui.components.AppTopBar
import com.fran.gametrackerdefran.ui.components.GameForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGameScreen(
    navController: NavController,
    gameId: Int
) {

    val game = GameRepository.getGameById(gameId)

    Scaffold(

        topBar = {

            AppTopBar(
                title = "Editar juego",
                showBackButton = true,
                onBackClick = {
                    navController.popBackStack()
                }
            )

        }

    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {

            GameForm(
                game = game,
                onSave = {
                    navController.popBackStack()
                }
            )

        }

    }

}