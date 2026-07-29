package com.fran.gametrackerdefran.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import com.fran.gametrackerdefran.ui.components.DropdownField
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.fran.gametrackerdefran.ui.components.GameCoverPreview
import com.fran.gametrackerdefran.ui.components.RawgSearchResults
@Composable
fun AddWishlistGameScreen(
    navController: NavController,
    gameViewModel: GameViewModel,
    wishlistGameId: Int? = null
) {
    val searchResults by gameViewModel.searchResults.collectAsState()
    val isLoading by gameViewModel.isLoading.collectAsState()
    var title by remember { mutableStateOf("") }
    var platform by remember { mutableStateOf("") }
    var coverImageUri by remember { mutableStateOf("") }
    val wishlistGame by remember(wishlistGameId) {
        if (wishlistGameId != null) {
            gameViewModel.getWishlistGameById(wishlistGameId)
        } else {
            null
        }
    }?.collectAsState(initial = null) ?: remember { mutableStateOf(null) }
    LaunchedEffect(wishlistGame) {
        wishlistGame?.let { game ->
            title = game.title
            platform = game.platform
            coverImageUri = game.coverImageUri.orEmpty()
        }
    }
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
            GameCoverPreview(coverImageUri)

            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                },

                label = {
                    Text("Nombre")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        gameViewModel.searchGames(title)
                    }
                },
                enabled = !isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Buscar en RAWG")
                }
            }

            RawgSearchResults(
                games = searchResults,
                onGameSelected = { game ->

                    title = game.name
                    coverImageUri = game.background_image ?: ""

                    gameViewModel.clearSearchResults()
                }
            )

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

                        if (wishlistGame == null) {

                            gameViewModel.insertWishlistGame(
                                WishlistGame(
                                    title = title.trim(),
                                    platform = platform.trim(),
                                    coverImageUri = coverImageUri.ifBlank { null }
                                )
                            )

                        } else {

                            gameViewModel.updateWishlistGame(
                                wishlistGame!!.copy(
                                    title = title.trim(),
                                    platform = platform.trim(),
                                    coverImageUri = coverImageUri.ifBlank { null }
                                )
                            )

                        }

                        navController.popBackStack()
                    }
                }
            ) {
                Text(
                    if (wishlistGame == null) {
                        "Guardar"
                    } else {
                        "Actualizar"
                    }
                )
            }
        }
    }
}