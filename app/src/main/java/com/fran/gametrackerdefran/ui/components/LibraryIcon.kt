package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fran.gametrackerdefran.R
import androidx.compose.ui.graphics.Color
@Composable
fun LibraryIcon(
    library: String
) {

    val icon = when (library) {
        "Steam" -> R.drawable.ic_steam
        "Steam Deck" -> R.drawable.ic_steam_deck
        "Epic Games" -> R.drawable.ic_epic_games
        "EA App" -> R.drawable.ic_ea
        "Battle.net" -> R.drawable.ic_battlenet
        "Ubisoft Connect" -> R.drawable.ic_ubisoft
        "GOG" -> R.drawable.ic_gog
        "Xbox App" -> R.drawable.ic_xbox
        "Nintendo Switch" -> R.drawable.ic_nintendo_switch2


        else -> R.drawable.ic_steam
    }

    Icon(
        painter = painterResource(id = icon),
        contentDescription = library,
        modifier = Modifier.size(22.dp),
        tint = Color.Unspecified
    )

}