package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fran.gametrackerdefran.data.model.Game

@Composable
fun GameCard(
    game: Game,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // Cabecera
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = game.nombre,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(12.dp))

                RatingStars(
                    rating = game.rating
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            // Plataforma y horas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                RowWithIcon(
                    icon = {
                        androidx.compose.material3.Icon(
                            Icons.Default.SportsEsports,
                            contentDescription = null
                        )
                    },
                    text = game.plataforma
                )

                RowWithIcon(
                    icon = {
                        androidx.compose.material3.Icon(
                            Icons.Default.Schedule,
                            contentDescription = null
                        )
                    },
                    text = "${game.horas} h"
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

            StatusChip(
                status = game.estado
            )

            if (game.comentario.isNotBlank()) {

                Spacer(modifier = Modifier.height(12.dp))

                HorizontalDivider()

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = game.comentario,
                    style = MaterialTheme.typography.bodyMedium
                )

            }

        }

    }

}