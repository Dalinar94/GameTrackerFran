package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fran.gametrackerdefran.data.estados
import com.fran.gametrackerdefran.data.model.Game
import com.fran.gametrackerdefran.data.model.GameFormErrors
import com.fran.gametrackerdefran.data.model.GameFormState
import com.fran.gametrackerdefran.data.plataformas
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel
import com.fran.gametrackerdefran.utils.ImageDownloader
import com.fran.gametrackerdefran.utils.getCurrentDate
import com.fran.gametrackerdefran.validation.GameFormValidator
import com.fran.gametrackerdefran.ui.components.GameCoverPreview
import android.app.DatePickerDialog
import android.util.Log
import java.util.Calendar
@Composable
fun GameForm(
    gameViewModel: GameViewModel,
    game: Game? = null,
    onSave: () -> Unit
) {

    var errors by remember { mutableStateOf(GameFormErrors()) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
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
                    estado = game.estado,
                    fechaCompletado = game.fechaCompletado ?: "",
                    portadaUri = game.portadaUri ?: ""
                )
            }
        )
    }

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val searchResults by gameViewModel.searchResults.collectAsState()
    val isLoading by gameViewModel.isLoading.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        GameCoverPreview(formState.portadaUri)

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

        Button(
            onClick = {
                gameViewModel.searchGames(formState.nombre)
            },
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )

            } else {

                Text("Buscar portada")

            }
        }
        RawgSearchResults(
            games = searchResults,
            onGameSelected = { game ->

                formState = formState.copy(
                    nombre = game.name,
                    portadaUri = game.background_image ?: ""
                )

                gameViewModel.clearSearchResults()
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
            onOptionSelected = { nuevoEstado ->

                formState = formState.copy(
                    estado = nuevoEstado,

                    fechaCompletado =
                        when {

                            nuevoEstado != com.fran.gametrackerdefran.data.model.GameStatus.COMPLETADO ->
                                ""

                            formState.fechaCompletado.isBlank() ->
                                getCurrentDate()

                            else ->
                                formState.fechaCompletado
                        }

                )

            },
            isError = errors.estado != null,
            errorMessage = errors.estado
        )

        //fecha
        if (formState.estado == com.fran.gametrackerdefran.data.model.GameStatus.COMPLETADO) {

            CompletionDateField(
                date = formState.fechaCompletado,
                onClick = {
                    showDatePicker = true
                }
            )

        }


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
                        estado = formState.estado,
                        fechaCompletado = formState.fechaCompletado,
                        portadaUri = formState.portadaUri
                    )

                    val result = GameFormValidator.validate(form)

                    errors = result.errors

                if (result.isValid) {
                    val portadaLocal = if (formState.portadaUri.startsWith("http")) {

                        ImageDownloader.downloadImage(
                            context = context,
                            imageUrl = formState.portadaUri,
                            fileName = formState.nombre
                        ) ?: formState.portadaUri

                    } else {

                        formState.portadaUri

                    }
                    val gameToSave = Game(
                        id = game?.id ?: 0,
                        nombre = formState.nombre,
                        plataforma = formState.plataforma,
                        horas = formState.horas.toInt(),
                        rating = formState.rating,
                        comentario = formState.comentario,
                        estado = formState.estado!!,
                        favorito = game?.favorito ?: false,
                        fechaCompletado = formState.fechaCompletado.ifBlank { null },
                        portadaUri = portadaLocal.ifBlank { null }
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
    if (showDatePicker) {

        val calendar = Calendar.getInstance()

        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->

                Log.d(
                    "GameForm",
                    "Fecha seleccionada: $year-${month + 1}-$dayOfMonth"
                )

                formState = formState.copy(
                    fechaCompletado = String.format(
                        "%04d-%02d-%02d",
                        year,
                        month + 1,
                        dayOfMonth
                    )
                )

                showDatePicker = false


            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)

        ).apply {

            setOnDismissListener {
                showDatePicker = false
            }

        }.show()

    }
}