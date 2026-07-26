package com.fran.gametrackerdefran.ui.screens


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Button
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.fran.gametrackerdefran.ui.components.DetailSection
import com.fran.gametrackerdefran.ui.components.GameInfoRow
import com.fran.gametrackerdefran.ui.components.LibraryIcon
import com.fran.gametrackerdefran.ui.components.StarRating
import com.fran.gametrackerdefran.ui.theme.GTRadius
import com.fran.gametrackerdefran.ui.theme.GTSpacing
import java.io.File
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(

    gameId: Int,
    gameViewModel: GameViewModel,
    onEdit: (Int) -> Unit,
    onBack: () -> Unit
) {
    val game = gameViewModel.findGameById(gameId)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Detalle del juego")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        if (game == null) {

            Text(
                text = "Juego no encontrado",
                modifier = Modifier.padding(paddingValues)
            )

        } else {

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(GTSpacing.Medium)
            ) {
                if (game.portadaUri != null) {

                    AsyncImage(
                        model = File(game.portadaUri),
                        contentDescription = game.nombre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(GTRadius.Large)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(GTSpacing.Medium))
                }
                Text(
                    text = game.nombre,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                DetailSection(
                    title = "Información"
                ) {
                    GameInfoRow(
                        icon = {
                            LibraryIcon(
                                library = game.plataforma
                            )
                        },
                        text = game.plataforma
                    )

                    GameInfoRow(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null
                            )
                        },
                        text = game.estado.displayName
                    )

                    GameInfoRow(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null
                            )
                        },
                        text = "${game.horas} horas"
                    )



                    if (!game.fechaCompletado.isNullOrBlank()) {

                        GameInfoRow(
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Event,
                                    contentDescription = null
                                )
                            },
                            text = "Completado el ${game.fechaCompletado}"
                        )

                    }


                    if (game.favorito) {

                        GameInfoRow(
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null,
                                    tint = Color.Red
                                )
                            },
                            text = "Marcado como favorito"
                        )
                    }
                }

                DetailSection(
                    title = "Valoración"
                ) {
                    StarRating(
                        rating = game.rating,
                        enabled = false
                    )

                }

                if (!game.comentario.isBlank()) {

                    DetailSection(
                        title = "Comentario"
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Column(
                                modifier = Modifier.padding(GTSpacing.Medium)
                            ) {

                                Text(
                                    text = game.comentario,
                                    style = MaterialTheme.typography.bodyLarge
                                )

                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(GTSpacing.Medium))

                Button(
                    onClick = {
                        onEdit(game.id)
                    }
                ) {
                    Text("Editar")
                }
            }

        }
    }
}