package com.fran.gametrackerdefran.data.local

import androidx.room.TypeConverter
import com.fran.gametrackerdefran.data.model.GameStatus

class GameConverters {

    @TypeConverter
    fun fromGameStatus(status: GameStatus): String {
        return status.name
    }

    @TypeConverter
    fun toGameStatus(value: String): GameStatus {
        return GameStatus.valueOf(value)
    }
}