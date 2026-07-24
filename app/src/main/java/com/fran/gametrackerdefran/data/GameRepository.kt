package com.fran.gametrackerdefran.data

import com.fran.gametrackerdefran.data.dao.GameDao
import com.fran.gametrackerdefran.data.model.Game
import kotlinx.coroutines.flow.Flow

class GameRepository(
    private val gameDao: GameDao
) {

    val games: Flow<List<Game>> = gameDao.getAllGames()

    suspend fun addGame(game: Game) {
        gameDao.insertGame(game)
    }
}