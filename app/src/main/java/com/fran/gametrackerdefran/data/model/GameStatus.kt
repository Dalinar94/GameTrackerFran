package com.fran.gametrackerdefran.data.model

enum class GameStatus(
    val displayName: String
) {

    PENDIENTE("Pendiente"),

    JUGANDO("Jugando"),

    COMPLETADO("Completado"),

    ABANDONADO("Abandonado")

}