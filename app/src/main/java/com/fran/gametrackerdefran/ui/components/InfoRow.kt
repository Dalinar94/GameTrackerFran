package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fran.gametrackerdefran.ui.theme.GTSpacing

@Composable
fun InfoRow(
    icon: @Composable () -> Unit,
    label: String,
    value: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        icon()

        Spacer(
            modifier = Modifier.width(GTSpacing.Small)
        )

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.width(GTSpacing.Small)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

    }

}