package com.fran.gametrackerdefran.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fran.gametrackerdefran.data.entity.WishlistGame
import com.fran.gametrackerdefran.data.plataformas
import com.fran.gametrackerdefran.ui.components.AppTopBar
import com.fran.gametrackerdefran.ui.components.CoverPicker
import com.fran.gametrackerdefran.ui.components.DropdownField
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel

@Composable
fun AddWishlistGameScreen(
    navController: NavController,
    gameViewModel: GameViewModel
) {

    var title by remember { mutableStateOf("") }
    var platform by remember { mutableStateOf("") }
    var coverImageUri by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Añadir juego deseado",
                showBackButton = true,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CoverPicker(
                imageUri = coverImageUri,
                onImageSelected = { uri ->
                    coverImageUri = uri
                }
            )
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                },

                label = {
                    Text("Nombre")
                },
                modifier = Modifier.fillMaxWidth()            )
            DropdownField(
                label = "Plataforma",
                options = plataformas,
                selectedOption = platform,
                optionLabel = { it },
                onOptionSelected = {
                    platform = it
                }
            )
            Button(
                onClick = {

                    if (title.isNotBlank() && platform.isNotBlank()) {

                        gameViewModel.insertWishlistGame(
                            WishlistGame(
                                title = title,
                                platform = platform,
                                coverImageUri = coverImageUri.ifBlank { null }
                            )
                        )

                        navController.popBackStack()
                    }
                }
            ) {
                Text("Guardar")
            }
        }
    }
}