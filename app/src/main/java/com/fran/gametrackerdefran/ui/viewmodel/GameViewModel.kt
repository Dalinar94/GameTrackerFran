package com.fran.gametrackerdefran.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fran.gametrackerdefran.data.GameRepository
import com.fran.gametrackerdefran.data.model.Game
import com.fran.gametrackerdefran.data.model.GameSortOption
import com.fran.gametrackerdefran.data.model.GameStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GameViewModel(
    private val repository: GameRepository
) : ViewModel() {

    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games

    init {
        viewModelScope.launch {
            repository.games.collectLatest {
                _games.value = it
            }
        }
    }

    var selectedFilter by mutableStateOf<GameStatus?>(null)
        private set

    var selectedSort by mutableStateOf(GameSortOption.NAME)
        private set

    var searchQuery by mutableStateOf("")
        private set

    val filteredGames: List<Game>
        get() {

            val filtered = games.value.filter { game ->

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
        get() = games.value.size

    val completedGames: Int
        get() = games.value.count {
            it.estado == GameStatus.COMPLETADO
        }

    val playingGames: Int
        get() = games.value.count {
            it.estado == GameStatus.JUGANDO
        }

    val pendingGames: Int
        get() = games.value.count {
            it.estado == GameStatus.PENDIENTE
        }

    val abandonatedGames: Int
        get() = games.value.count {
            it.estado == GameStatus.ABANDONADO
        }

    val averageRating: Double
        get() =
            if (games.value.isEmpty()) {
                0.0
            } else {
                games.value.map { it.rating }.average()
            }

    val completionPercentage: Int
        get() =
            if (games.value.isEmpty()) {
                0
            } else {
                (completedGames * 100) / totalGames
            }

    val gamesByPlatform: List<Pair<String, Int>>
        get() =
            games.value
                .groupingBy { it.plataforma }
                .eachCount()
                .toList()
                .sortedByDescending { it.second }

    fun setFilter(filter: GameStatus?) {
        selectedFilter = filter
    }

    fun setSort(sort: GameSortOption) {
        selectedSort = sort
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun addGame(game: Game) {
        viewModelScope.launch {
            repository.insertGame(game)
        }
    }

    fun updateGame(game: Game) {
        viewModelScope.launch {
            repository.updateGame(game)
        }
    }

    fun toggleFavorite(game: Game) {
        viewModelScope.launch {
            repository.updateGame(
                game.copy(
                    favorito = !game.favorito
                )
            )
        }
    }

    fun deleteGame(game: Game) {
        viewModelScope.launch {
            repository.deleteGame(game)
        }
    }
    suspend fun getGameById(id: Int): Game? {
        return repository.getGameById(id)
    }
    fun findGameById(id: Int): Game? {
        return games.value.find { it.id == id }
    }
}