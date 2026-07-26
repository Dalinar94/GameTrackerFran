package com.fran.gametrackerdefran.data

import com.fran.gametrackerdefran.data.model.GameStatus
import com.fran.gametrackerdefran.data.model.Platform

val plataformas = Platform.entries.map { it.displayName }

val estados = GameStatus.entries