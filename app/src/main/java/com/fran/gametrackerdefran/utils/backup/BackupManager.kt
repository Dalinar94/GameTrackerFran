package com.fran.gametrackerdefran.utils.backup

import com.fran.gametrackerdefran.data.model.Game
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import android.content.Context
import java.io.File
import com.google.gson.reflect.TypeToken
import android.net.Uri
object BackupManager {

    private val gson: Gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    fun exportToJson(games: List<Game>): String {
        return gson.toJson(games)
    }
    fun importFromJson(json: String): List<Game> {

        val type = object : TypeToken<List<Game>>() {}.type

        return gson.fromJson(json, type)

    }
    fun saveToFile(
        context: Context,
        json: String
    ): File {

        val backupFile = File(
            context.cacheDir,
            "games_backup.json"
        )

        backupFile.writeText(json)

        return backupFile
    }

    fun readJsonFromUri(
        context: Context,
        uri: Uri
    ): String {

        return context.contentResolver
            .openInputStream(uri)
            ?.bufferedReader()
            ?.use { it.readText() }
            ?: throw IllegalArgumentException("No se pudo leer el archivo")

    }

    fun saveToUri(
        context: Context,
        uri: Uri,
        json: String
    ) {

        context.contentResolver
            .openOutputStream(uri)
            ?.bufferedWriter()
            ?.use { it.write(json) }

    }
}