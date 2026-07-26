package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fran.gametrackerdefran.data.model.GameSortOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortMenu(
    selectedSort: GameSortOption,
    onSortSelected: (GameSortOption) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        OutlinedTextField(
            value = selectedSort.displayName,
            onValueChange = {},
            readOnly = true,
            label = {
                Text("Ordenar por")
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            GameSortOption.entries.forEach { option ->

                DropdownMenuItem(
                    text = {
                        Text(option.displayName)
                    },
                    onClick = {

                        onSortSelected(option)
                        expanded = false

                    }
                )

            }

        }

    }

}
