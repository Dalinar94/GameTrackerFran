package com.fran.gametrackerdefran.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fran.gametrackerdefran.data.GameRepository
import com.fran.gametrackerdefran.data.model.Game
import com.fran.gametrackerdefran.data.model.GameStatus
import com.fran.gametrackerdefran.data.model.GameSortOption
class GameViewModel : ViewModel() {

    val games = GameRepository.games

    var selectedFilter: GameStatus? by mutableStateOf(null)
        private set

    var selectedSort by mutableStateOf(GameSortOption.NAME)
        private set
    var searchQuery by mutableStateOf("")
        private set
    val filteredGames: List<Game>
        get() {

            val filtered = games.filter { game ->

                val matchesFilter =
                    selectedFilter == null ||
                            game.estado == selectedFilter

                val matchesSearch =
                    game.nombre.contains(
                        searchQuery,
                        ignoreCase = true
                    )

                matchesFilter && matchesSearch

            }

            return when (selectedSort) {

                GameSortOption.NAME ->
                    filtered.sortedBy { it.nombre }

                GameSortOption.HOURS ->
                    filtered.sortedByDescending { it.horas }

                GameSortOption.RATING ->
                    filtered.sortedByDescending { it.rating }

                GameSortOption.STATUS ->
                    filtered.sortedBy { it.estado.displayName }

            }

        }
    val totalGames: Int
        get() = games.size

    val completedGames: Int
        get() = games.count {
            it.estado == GameStatus.COMPLETADO
        }

    val playingGames: Int
        get() = games.count {
            it.estado == GameStatus.JUGANDO
        }

    val pendingGames: Int
        get() = games.count {
            it.estado == GameStatus.PENDIENTE
        }
    val abandonatedGames: Int
        get() = games.count {
            it.estado == GameStatus.ABANDONADO
        }
    val averageRating: Double
        get() {

            if (games.isEmpty()) {
                return 0.0
            }

            return games
                .map { it.rating }
                .average()

        }

    val completionPercentage: Int
        get() {

            if (games.isEmpty()) {
                return 0
            }

            return (completedGames * 100) / totalGames

        }

    fun setFilter(filter: GameStatus?) {
        selectedFilter = filter
    }
    fun setSort(sort: GameSortOption) {
        selectedSort = sort
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

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }
}