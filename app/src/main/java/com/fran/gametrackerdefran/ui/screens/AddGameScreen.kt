package com.fran.gametrackerdefran.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import com.fran.gametrackerdefran.ui.components.AppTopBar
import com.fran.gametrackerdefran.ui.components.GameForm
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGameScreen( navController: NavController,
                   gameViewModel: GameViewModel
) {

    Scaffold(

        topBar = {

            AppTopBar(
                title = "Nuevo juego",
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
                gameViewModel = gameViewModel,
                onSave = {
                    navController.popBackStack()
                }
            )
        }

    }

}