package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fran.gametrackerdefran.ui.theme.GTSpacing

@Composable
fun DetailSection(
    title: String,
    content: @Composable () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = GTSpacing.Large)
    ){

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        Column(
            modifier = Modifier.padding(top = GTSpacing.Small)
        ) {
            content()
        }

    }

}