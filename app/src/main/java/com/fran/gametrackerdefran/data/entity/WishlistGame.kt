package com.fran.gametrackerdefran.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist_games")
data class WishlistGame(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val platform: String,

    val coverImageUri: String? = null
)