package com.fran.gametrackerdefran.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fran.gametrackerdefran.data.GameRepository
import com.fran.gametrackerdefran.data.model.Game
import com.fran.gametrackerdefran.data.model.GameStatus

class GameViewModel : ViewModel() {

    val games = GameRepository.games

    var selectedFilter: GameStatus? by mutableStateOf(null)
        private set

    val filteredGames: List<Game>
        get() = if (selectedFilter == null) {
            games
        } else {
            games.filter { it.estado == selectedFilter }
        }

    fun setFilter(filter: GameStatus?) {
        selectedFilter = filter
    }

    fun addGame(game: Game) {
        GameRepository.games.add(game)
    }

    fun updateGame(game: Game) {
        GameRepository.updateGame(game)
    }

    fun deleteGame(game: Game) {
        GameRepository.deleteGame(game)
    }

}