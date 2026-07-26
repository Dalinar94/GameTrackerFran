package com.fran.gametrackerdefran.ui.components
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fran.gametrackerdefran.ui.components.LibraryIcon
import com.fran.gametrackerdefran.ui.theme.GTSpacing
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.ui.unit.dp
@Composable
fun PlatformDistributionRow(
    platform: String,
    value: Int,
    progress: Float
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = GTSpacing.Small),
            verticalAlignment = Alignment.CenterVertically
        ) {

            LibraryIcon(
                library = platform
            )

            Spacer(modifier = Modifier.width(GTSpacing.Medium))

            Text(
                text = platform,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "$value (${(progress * 100).toInt()}%)",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 36.dp,
                    top = GTSpacing.Small,
                    bottom = GTSpacing.Small
                )
        )

        HorizontalDivider()
    }
}