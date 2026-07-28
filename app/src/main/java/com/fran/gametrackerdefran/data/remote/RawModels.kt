package com.fran.gametrackerdefran.data.remote

data class RawgResponse(
    val results: List<RawgGame>
)

data class RawgGame(
    val id: Int,
    val name: String,
    val background_image: String?
)