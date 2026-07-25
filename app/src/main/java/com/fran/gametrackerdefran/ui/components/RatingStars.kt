package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RatingStars(
    rating: Int,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {

        repeat(5) { index ->

            Icon(
                imageVector =
                    if (index < rating)
                        Icons.Filled.Star
                    else
                        Icons.Outlined.StarBorder,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

        }

    }

}