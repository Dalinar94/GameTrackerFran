package com.fran.gametrackerdefran.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.fran.gametrackerdefran.data.GameRepository
import com.fran.gametrackerdefran.data.model.Game

class GameViewModel : ViewModel() {

    val games = GameRepository.games

    fun addGame(game: Game) {
        GameRepository.games.add(game)
    }
    fun updateGame(game: Game) {
        GameRepository.updateGame(game)
    }
}