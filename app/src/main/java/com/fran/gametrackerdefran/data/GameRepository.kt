package com.fran.gametrackerdefran.data

import com.fran.gametrackerdefran.data.local.GameDao
import com.fran.gametrackerdefran.data.model.Game
import kotlinx.coroutines.flow.Flow

class GameRepository(
    private val gameDao: GameDao
) {

    val games: Flow<List<Game>> = gameDao.getAllGames()

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun updateGame(game: Game) {
        gameDao.updateGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }
    suspend fun getGameById(id: Int): Game? {
        return gameDao.getGameById(id)
    }
}