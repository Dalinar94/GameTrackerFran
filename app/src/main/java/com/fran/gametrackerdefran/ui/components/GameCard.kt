package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fran.gametrackerdefran.data.model.Game
import com.fran.gametrackerdefran.ui.theme.GTElevation
import com.fran.gametrackerdefran.ui.theme.GTRadius
import com.fran.gametrackerdefran.ui.theme.GTSpacing

@Composable
fun GameCard(
    game: Game,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = GTSpacing.Medium,
                vertical = GTSpacing.Small
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(GTRadius.Large),
        elevation = CardDefaults.cardElevation(
            defaultElevation = GTElevation.Card
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(GTSpacing.Large)
        ) {

            // Portada
            GameCover(
                imageUri = game.portadaUri,
                contentDescription = game.nombre
            )

            Spacer(modifier = Modifier.width(GTSpacing.Medium))

            // Información
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {

                    Text(
                        text = game.nombre,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    FavoriteButton(
                        isFavorite = game.favorito,
                        onClick = onFavoriteClick
                    )

                }

                Spacer(modifier = Modifier.height(GTSpacing.Small))

                RatingStars(
                    rating = game.rating
                )

                Spacer(modifier = Modifier.height(GTSpacing.Medium))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    LibraryIcon(
                        library = game.plataforma,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = game.plataforma,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(GTSpacing.Small))

                InfoRow(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null
                        )
                    },
                    label = "Horas",
                    value = "${game.horas} h"
                )
                if (!game.fechaCompletado.isNullOrBlank()) {

                    Spacer(modifier = Modifier.height(GTSpacing.Small))

                    InfoRow(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = null
                            )
                        },
                        label = "Finalizado",
                        value = game.fechaCompletado
                    )

                }

                Spacer(modifier = Modifier.height(GTSpacing.Medium))

                StatusChip(
                    status = game.estado
                )

                if (game.comentario.isNotBlank()) {

                    Spacer(modifier = Modifier.height(GTSpacing.Medium))

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(GTSpacing.Medium))

                    Text(
                        text = "Comentario",
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.height(GTSpacing.Small))

                    Text(
                        text = game.comentario,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}