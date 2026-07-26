package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fran.gametrackerdefran.data.model.GameStatus

@Composable
fun StatusFilterBar(
    selectedFilter: GameStatus?,
    onFilterSelected: (GameStatus?) -> Unit
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item {

            FilterChip(
                selected = selectedFilter == null,
                onClick = {
                    onFilterSelected(null)
                },
                label = {
                    Text("Todos")
                }
            )

        }

        GameStatus.entries.forEach { status ->

            item {

                FilterChip(
                    selected = selectedFilter == status,
                    onClick = {
                        onFilterSelected(status)
                    },
                    label = {
                        Text(status.displayName)
                    }
                )

            }

        }

    }

}