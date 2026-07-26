package com.fran.gametrackerdefran.data.model

import androidx.annotation.DrawableRes
import com.fran.gametrackerdefran.R

enum class Platform(
    val displayName: String,
    @DrawableRes val icon: Int
) {

    STEAM(
        "Steam",
        R.drawable.ic_steam
    ),

    EPIC(
        "Epic Games",
        R.drawable.ic_epic_games
    ),

    GOG(
        "GOG",
        R.drawable.ic_gog
    ),
    UBISOFT(
        "Ubisoft Connect",
        R.drawable.ic_ubisoft
    ),

    EA(
        "EA App",
        R.drawable.ic_ea
    ),

    BATTLENET(
        "Battle.net",
        R.drawable.ic_battlenet
    ),
    STEAM_DECK(
        "Steam Deck",
        R.drawable.ic_steam_deck
    ),
    ROCKSTAR(
        "Rockstar Game Launcher",
        R.drawable.ic_rockstar
    ),

    XBOX(
        "Xbox App",
        R.drawable.ic_xbox
    ),

    NINTENDO_SWITCH(
        "Nintendo Switch",
        R.drawable.ic_nintendo_switch2
    ),

    OTHER(
        "Otro",
        R.drawable.ic_steam
    )

}