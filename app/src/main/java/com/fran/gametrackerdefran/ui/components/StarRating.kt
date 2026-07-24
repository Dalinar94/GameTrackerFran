package com.fran.gametrackerdefran.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun StarRating(
    rating: Int,
    onRatingChanged: (Int) -> Unit
) {

    Row {

        for (i in 1..5) {

            Icon(
                imageVector =
                    if (i <= rating)
                        Icons.Filled.Star
                    else
                        Icons.Outlined.Star,

                contentDescription = "Estrella $i",

                modifier = androidx.compose.ui.Modifier
                    .clickable {

                        onRatingChanged(i)

                    }

            )

        }

    }

}