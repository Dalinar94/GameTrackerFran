package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fran.gametrackerdefran.data.remote.RawgGame

@Composable
fun RawgSearchResults(
    games: List<RawgGame>,
    onGameSelected: (RawgGame) -> Unit
) {
    if (games.isNotEmpty()) {

        Text(
            text = "Resultados encontrados",
            style = MaterialTheme.typography.titleMedium
        )

        games.take(5).forEach { game ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                onClick = {
                    onGameSelected(game)
                }
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    AsyncImage(
                        model = game.background_image,
                        contentDescription = game.name,
                        modifier = Modifier.size(60.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = game.name,
                        modifier = Modifier.padding(top = 18.dp)
                    )
                }
            }
        }
    }
}