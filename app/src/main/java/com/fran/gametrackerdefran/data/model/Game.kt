package com.fran.gametrackerdefran.data.model

data class Game(
    val id: Int,
    val nombre: String,
    val plataforma: String,
    val horas: Int,
    val rating: Int,
    val comentario: String,
    val estado: GameStatus
)