package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fran.gametrackerdefran.data.estados
import com.fran.gametrackerdefran.data.model.GameFormState
import com.fran.gametrackerdefran.data.plataformas
import com.fran.gametrackerdefran.validation.GameFormValidator
import com.fran.gametrackerdefran.data.model.GameFormErrors
import com.fran.gametrackerdefran.data.model.Game
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel
@Composable
fun GameForm(
    gameViewModel: GameViewModel,
    game: Game? = null,
    onSave: () -> Unit
) {
    var errors by remember { mutableStateOf(GameFormErrors()) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var formState by remember(game) {
        mutableStateOf(
            if (game == null) {
                GameFormState()
            } else {
                GameFormState(
                    nombre = game.nombre,
                    plataforma = game.plataforma,
                    horas = game.horas.toString(),
                    rating = game.rating,
                    comentario = game.comentario,
                    estado = game.estado
                )
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {

        OutlinedTextField(
            value = formState.nombre,
            onValueChange = {
                formState = formState.copy(
                    nombre = it
                )
            },
            label = { Text("Nombre") },
            isError = errors.nombre != null,
            supportingText = {
                errors.nombre?.let {
                    Text(it)
                }
            }
        )

        DropdownField(
            label = "Biblioteca",
            options = plataformas,
            selectedOption = formState.plataforma,
            optionLabel = { it },
            onOptionSelected = {
                formState=formState.copy(
                    plataforma = it
                )
            } ,
            isError = errors.plataforma != null,
            errorMessage = errors.plataforma
        )

        OutlinedTextField(
            value = formState.horas,
            onValueChange = {
                formState = formState.copy(
                horas = it
            )},
            label = { Text("Horas") },
            modifier = Modifier.fillMaxWidth(),
                    isError = errors.horas != null,
            supportingText = {
                errors.horas?.let {
                    Text(it)
                }
            },
        )

        DropdownField(
            label = "Estado",
            options = estados,
            selectedOption = formState.estado,
            optionLabel = { it.displayName },
            onOptionSelected = {
                formState = formState.copy(
                    estado = it
                )
            },
            isError = errors.estado != null,
            errorMessage = errors.estado
        )
        Text("Valoración")

        StarRating(

            rating = formState.rating,

            onRatingChanged = {
                formState = formState.copy(
                    rating = it
                )

            },

            isError = errors.rating != null,

            errorMessage = errors.rating

        )

        OutlinedTextField(
            value = formState.comentario,
            onValueChange = {
                formState = formState.copy(
                comentario = it
            ) },
            label = { Text("Comentario") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {

                    val form = GameFormState(
                        nombre = formState.nombre,
                        plataforma = formState.plataforma,
                        horas = formState.horas,
                        rating = formState.rating,
                        comentario =formState.comentario,
                        estado = formState.estado
                    )

                    val result = GameFormValidator.validate(form)

                    errors = result.errors

                if (result.isValid) {

                    val gameToSave = Game(
                        id = game?.id ?: 0,
                        nombre = formState.nombre,
                        plataforma = formState.plataforma,
                        horas = formState.horas.toInt(),
                        rating = formState.rating,
                        comentario = formState.comentario,
                        estado = formState.estado!!
                    )

                    if (game == null) {
                        gameViewModel.addGame(gameToSave)
                    } else {
                        gameViewModel.updateGame(gameToSave)
                    }

                    onSave()
                }


            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                if (game == null) {
                    "Guardar"
                } else {
                    "Actualizar"
                }
            )

        }
        if (game != null) {

            OutlinedButton(
                onClick = {
                    showDeleteDialog = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Eliminar")
            }

        }
    }
    if (showDeleteDialog && game != null) {

        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },

            title = {
                Text("Eliminar juego")
            },

            text = {
                Text(
                    "¿Seguro que quieres eliminar \"${game.nombre}\"?\n\nEsta acción no se puede deshacer."
                )
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        gameViewModel.deleteGame(game)
                        showDeleteDialog = false
                        onSave()
                    }
                ) {
                    Text("Eliminar")
                }

            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showDeleteDialog = false
                    }
                ) {
                    Text("Cancelar")
                }

            }

        )

    }
}