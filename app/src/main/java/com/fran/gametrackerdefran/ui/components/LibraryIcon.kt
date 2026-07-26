package com.fran.gametrackerdefran.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun LibraryIcon(
    library: String
) {

    val icon: ImageVector = when (library) {

        "Steam" -> Icons.Default.Computer

        "Epic Games" -> Icons.Default.VideogameAsset

        "EA App" -> Icons.Default.SportsEsports

        "Battle.net" -> Icons.Default.SportsEsports

        "Ubisoft Connect" -> Icons.Default.SportsEsports

        "GOG" -> Icons.Default.Computer

        "Xbox App" -> Icons.Default.SportsEsports

        "Nintendo Switch" -> Icons.Default.VideogameAsset

        "Rockstar Games Launcher" -> Icons.Default.SportsEsports

        else -> Icons.Default.SportsEsports
    }

    Icon(
        imageVector = icon,
        contentDescription = library
    )

}