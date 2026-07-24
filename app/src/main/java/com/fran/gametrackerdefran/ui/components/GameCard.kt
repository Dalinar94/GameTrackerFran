package com.fran.gametrackerdefran.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fran.gametrackerdefran.data.model.Game

@Composable
fun GameCard(game: Game) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = game.nombre,
                style = MaterialTheme.typography.titleLarge
            )

            Text("Plataforma: ${game.plataforma}")
            Text("Horas: ${game.horas}")
            Text("Rating: ${game.rating}/5")
            Text("Estado: ${game.estado}")
            Text(game.comentario)

        }

    }

}