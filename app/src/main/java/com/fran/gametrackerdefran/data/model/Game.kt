package com.fran.gametrackerdefran.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String,
    val plataforma: String,
    val horas: Int,
    val rating: Int,
    val comentario: String,
    val estado: GameStatus,
    val favorito: Boolean = false,
    val fechaCompletado: String? = null,
    val portadaUri: String? = null
)