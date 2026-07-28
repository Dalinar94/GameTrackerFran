package com.fran.gametrackerdefran.data.local

import androidx.room.*
import com.fran.gametrackerdefran.data.model.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<Game>>

    @Insert
    suspend fun insertGame(game: Game)

    @Update
    suspend fun updateGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)
    @Query("SELECT * FROM games WHERE id = :id")
    suspend fun getGameById(id: Int): Game?

    @Query("DELETE FROM games")
    suspend fun deleteAll()

    @Insert
    suspend fun insertGames(games: List<Game>)
}