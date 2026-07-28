package com.fran.gametrackerdefran.data.repository

import com.fran.gametrackerdefran.data.entity.WishlistGame
import com.fran.gametrackerdefran.data.local.WishlistDao
import kotlinx.coroutines.flow.Flow
class WishlistRepository(
    private val wishlistDao: WishlistDao
) {

    val allWishlistGames: Flow<List<WishlistGame>> =
        wishlistDao.getAllWishlistGames()

    fun getWishlistGameById(id: Int): Flow<WishlistGame?> {
        return wishlistDao.getWishlistGameById(id)
    }
    suspend fun insert(game: WishlistGame) {
        wishlistDao.insertWishlistGame(game)
    }

    suspend fun update(game: WishlistGame) {
        wishlistDao.updateWishlistGame(game)
    }

    suspend fun delete(game: WishlistGame) {
        wishlistDao.deleteWishlistGame(game)
    }
}