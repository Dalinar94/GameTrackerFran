package com.fran.gametrackerdefran.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fran.gametrackerdefran.data.model.Game

@Database(
    entities = [Game::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GameConverters::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {

        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "game_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}