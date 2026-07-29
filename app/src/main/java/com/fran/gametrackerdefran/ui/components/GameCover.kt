package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fran.gametrackerdefran.ui.theme.GTElevation
import com.fran.gametrackerdefran.ui.theme.GTRadius

@Composable
fun GameCover(
    imageUri: String?,
    contentDescription: String?
) {

    Card(
        modifier = Modifier
            .width(80.dp)
            .height(120.dp),
        shape = RoundedCornerShape(GTRadius.Medium),
        elevation = CardDefaults.cardElevation(
            defaultElevation = GTElevation.Card
        )
    ) {

        if (!imageUri.isNullOrBlank()) {

            AsyncImage(
                model = imageUri,
                contentDescription = contentDescription,
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
                    contentDescription = null
                )

            }

        }

    }

}