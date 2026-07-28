package com.fran.gametrackerdefran.data.repository

import android.util.Log
import com.fran.gametrackerdefran.BuildConfig
import com.fran.gametrackerdefran.data.remote.RawgGame
import com.fran.gametrackerdefran.data.remote.RetrofitInstance

class RawgRepository {

    suspend fun searchGames(query: String): List<RawgGame> {
        val response = RetrofitInstance.api.searchGames(
            apiKey = BuildConfig.RAWG_API_KEY,
            query = query
        )
        Log.d("RAWG_RESULTS", "Resultados: ${response.results.size}")
        response.results.forEach {
            Log.d("RAWG_GAME", "${it.name} - ${it.background_image}")
        }
        return response.results
    }

}