package com.fran.gametrackerdefran.ui.components

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.fran.gametrackerdefran.data.model.GameStatus
import com.fran.gametrackerdefran.ui.theme.Error
import com.fran.gametrackerdefran.ui.theme.Info
import com.fran.gametrackerdefran.ui.theme.Success
import com.fran.gametrackerdefran.ui.theme.Warning

@Composable
fun StatusChip(
    status: GameStatus
) {

    val color = when (status) {
        GameStatus.COMPLETADO -> Success
        GameStatus.JUGANDO -> Info
        GameStatus.PENDIENTE -> Warning
        GameStatus.ABANDONADO -> Error
    }

    AssistChip(
        onClick = { },
        label = {
            Text(status.displayName)
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = color.copy(alpha = 0.15f),
            labelColor = color
        )
    )

}