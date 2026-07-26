package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fran.gametrackerdefran.ui.theme.GTElevation
import com.fran.gametrackerdefran.ui.theme.GTRadius
import com.fran.gametrackerdefran.ui.theme.GTSpacing
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun StatisticsSection(
    title: String,
    content: @Composable () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(GTRadius.Large),
        elevation = CardDefaults.cardElevation(
            defaultElevation = GTElevation.Card
        )
    ) {

        Column(
            modifier = Modifier.padding(GTSpacing.Large),
            verticalArrangement = Arrangement.spacedBy(GTSpacing.Medium)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )

            content()

        }

    }

}