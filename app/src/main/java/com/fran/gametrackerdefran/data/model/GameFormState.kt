package com.fran.gametrackerdefran.data.model

data class GameFormState(

    val nombre: String = "",

    val plataforma: String = "",

    val horas: String = "",

    val rating: Int = 0,

    val estado: GameStatus? = null,

    val comentario: String = "",

    val fechaCompletado: String = "",

    val portadaUri: String = ""

)