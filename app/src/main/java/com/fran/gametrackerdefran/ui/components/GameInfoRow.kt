package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fran.gametrackerdefran.ui.theme.GTSpacing

@Composable
fun GameInfoRow(
    icon: @Composable () -> Unit,
    text: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        icon()

        Spacer(
            modifier = Modifier.width(GTSpacing.Medium)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )

    }

    HorizontalDivider()

}