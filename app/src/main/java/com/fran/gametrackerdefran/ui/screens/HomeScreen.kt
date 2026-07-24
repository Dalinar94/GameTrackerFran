package com.fran.gametrackerdefran.ui.screens


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.fran.gametrackerdefran.data.Game
import com.fran.gametrackerdefran.ui.components.GameCard

@Composable
fun HomeScreen() {

    val juegos = listOf(

        Game(
            1,
            "Elden Ring",
            "PS5",
            120,
            5,
            "Obra maestra",
            "Completado"
        ),

        Game(
            2,
            "Cyberpunk 2077",
            "PC",
            35,
            4,
            "Muy divertido",
            "Jugando"
        )

    )

    LazyColumn {

        items(juegos) { juego ->

            GameCard(game = juego)

        }

    }

}