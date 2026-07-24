package com.fran.gametrackerdefran.data


import androidx.compose.runtime.mutableStateListOf
import com.fran.gametrackerdefran.data.model.Game
import com.fran.gametrackerdefran.data.model.GameStatus

object GameRepository {

    val games = mutableStateListOf(

        Game(
            id = 1,
            nombre = "Elden Ring",
            plataforma = "PS5",
            horas = 120,
            rating = 5,
            comentario = "Obra maestra",
            estado = GameStatus.COMPLETADO
        ),

        Game(
            id = 2,
            nombre = "Cyberpunk 2077",
            plataforma = "PC",
            horas = 35,
            rating = 4,
            comentario = "Muy divertido",
            estado = GameStatus.JUGANDO
        )

    )

}