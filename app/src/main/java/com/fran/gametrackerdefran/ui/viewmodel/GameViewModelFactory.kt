package com.fran.gametrackerdefran.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fran.gametrackerdefran.data.GameRepository
import com.fran.gametrackerdefran.data.repository.WishlistRepository
import com.fran.gametrackerdefran.data.repository.RawgRepository
class GameViewModelFactory(
    private val repository: GameRepository,
    private val wishlistRepository: WishlistRepository,
    private val rawgRepository: RawgRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(
                repository,
                wishlistRepository,
                rawgRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}