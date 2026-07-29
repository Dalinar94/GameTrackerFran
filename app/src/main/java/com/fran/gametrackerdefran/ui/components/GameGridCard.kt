package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.fran.gametrackerdefran.data.model.Game
import com.fran.gametrackerdefran.ui.theme.GTElevation
import com.fran.gametrackerdefran.ui.theme.GTRadius
import com.fran.gametrackerdefran.ui.theme.GTSpacing
import androidx.compose.foundation.layout.aspectRatio
@Composable
fun GameGridCard(
    game: Game,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .padding(GTSpacing.Small)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(GTRadius.Large),
        elevation = CardDefaults.cardElevation(
            defaultElevation = GTElevation.Card
        )
    ) {

        Column {

            GameCover(
                imageUri = game.portadaUri,
                contentDescription = game.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.68f)
            )

            Column(
                modifier = Modifier.padding(GTSpacing.Medium)
            ) {

                Text(
                    text = game.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(GTSpacing.Small))

                RatingStars(game.rating)

                Spacer(modifier = Modifier.height(GTSpacing.Small))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    LibraryIcon(
                        library = game.plataforma
                    )

                    FavoriteButton(
                        isFavorite = game.favorito,
                        onClick = onFavoriteClick
                    )

                }

            }

        }

    }

}