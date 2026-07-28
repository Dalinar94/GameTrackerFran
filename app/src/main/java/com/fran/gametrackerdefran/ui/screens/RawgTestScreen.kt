package com.fran.gametrackerdefran.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel

@Composable
fun RawgTestScreen(
    gameViewModel: GameViewModel
) {

    var query by remember {
        mutableStateOf("")
    }

    val results by gameViewModel.searchResults.collectAsState()

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                },
                label = {
                    Text("Buscar juego")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    gameViewModel.searchGames(query)
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Buscar")
            }
            Text(
                text = "Resultados: ${results.size}",
                modifier = Modifier.padding(top = 16.dp)
            )
            LazyColumn(
                modifier = Modifier.padding(top = 16.dp)
            ) {

                items(results) { game ->

                    Text(
                        text = game.name,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                }

            }

        }

    }

}