package com.fran.gametrackerdefran.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RawgApiService {

    @GET("games")
    suspend fun searchGames(
        @Query("key") apiKey: String,
        @Query("search") query: String
    ): RawgResponse

}