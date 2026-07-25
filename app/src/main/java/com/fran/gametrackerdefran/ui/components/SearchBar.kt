package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.IconButton
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        label = {
            Text("Buscar juego")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        trailingIcon = {

            if (query.isNotEmpty()) {

                IconButton(
                    onClick = {
                        onQueryChange("")
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Limpiar búsqueda"
                    )

                }

            }

        },
        singleLine = true
    )

}