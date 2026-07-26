package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fran.gametrackerdefran.data.model.Game
import com.fran.gametrackerdefran.ui.theme.GTElevation
import com.fran.gametrackerdefran.ui.theme.GTRadius
import com.fran.gametrackerdefran.ui.theme.GTSpacing
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

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

            // Portada (placeholder)
            Card(
                modifier = Modifier
                    .width(80.dp)
                    .height(120.dp),
                shape = RoundedCornerShape(GTRadius.Medium),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = GTElevation.Card
                )
            ) {

                if (!game.portadaUri.isNullOrBlank()) {

                    AsyncImage(
                        model = game.portadaUri,
                        contentDescription = game.nombre,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                } else {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.SportsEsports,
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)
                        )

                    }

                }

            }

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

                InfoRow(
                    icon = {
                        LibraryIcon(game.plataforma)
                    },
                    label = "Biblioteca",
                    value = game.plataforma
                )

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