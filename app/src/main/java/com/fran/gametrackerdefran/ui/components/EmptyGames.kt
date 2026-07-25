package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EmptyGames() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Icon(
            imageVector = Icons.Outlined.SportsEsports,
            contentDescription = null,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "No hay juegos",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "No hay juegos para mostrar con el filtro seleccionado.",
            style = MaterialTheme.typography.bodyMedium
        )

    }

}