package com.fran.gametrackerdefran.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.fran.gametrackerdefran.data.model.Game

class GameViewModel : ViewModel() {

    val games = `GameRepository.kt`.games

    fun addGame(game: Game) {
        `GameRepository.kt`.games.add(game)
    }

}