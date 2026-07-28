package com.fran.gametrackerdefran.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fran.gametrackerdefran.data.GameRepository
import com.fran.gametrackerdefran.data.repository.WishlistRepository

class GameViewModelFactory(
    private val repository: GameRepository,
    private val wishlistRepository: WishlistRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(repository,wishlistRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}