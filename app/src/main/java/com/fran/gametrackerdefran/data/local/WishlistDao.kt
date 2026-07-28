package com.fran.gametrackerdefran.data.local

import androidx.room.*
import com.fran.gametrackerdefran.data.entity.WishlistGame
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {

    @Query("SELECT * FROM wishlist_games ORDER BY title ASC")
    fun getAllWishlistGames(): Flow<List<WishlistGame>>

    @Insert
    suspend fun insertWishlistGame(game: WishlistGame)

    @Update
    suspend fun updateWishlistGame(game: WishlistGame)

    @Delete
    suspend fun deleteWishlistGame(game: WishlistGame)
}