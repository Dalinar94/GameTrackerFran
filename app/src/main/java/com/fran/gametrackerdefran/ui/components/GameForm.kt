package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fran.gametrackerdefran.data.estados
import com.fran.gametrackerdefran.data.model.GameFormState
import com.fran.gametrackerdefran.data.model.GameStatus
import com.fran.gametrackerdefran.data.plataformas
import com.fran.gametrackerdefran.ui.components.DropdownField
import com.fran.gametrackerdefran.validation.GameFormValidator
import com.fran.gametrackerdefran.data.model.GameFormErrors
@Composable
fun GameForm() {

    var nombre by remember { mutableStateOf("") }
    var plataforma by remember { mutableStateOf("") }
    var horas by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }
    var comentario by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf<GameStatus?>(null) }
    var errors by remember { mutableStateOf(GameFormErrors()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            isError = errors.nombre != null,
            supportingText = {
                errors.nombre?.let {
                    Text(it)
                }
            }
        )

        DropdownField(
            label = "Plataforma",
            options = plataformas,
            selectedOption = plataforma,
            optionLabel = { it },
            onOptionSelected = {
                plataforma = it
            } ,
            isError = errors.plataforma != null,
            errorMessage = errors.plataforma
        )

        OutlinedTextField(
            value = horas,
            onValueChange = { horas = it },
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
            selectedOption = estado,
            optionLabel = { it.displayName },
            onOptionSelected = {
                estado = it
            },
            isError = errors.estado != null,
            errorMessage = errors.estado
        )
        Text("Valoración")

        StarRating(

            rating = rating,

            onRatingChanged = {

                rating = it

            },

            isError = errors.rating != null,

            errorMessage = errors.rating

        )

        OutlinedTextField(
            value = comentario,
            onValueChange = { comentario = it },
            label = { Text("Comentario") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {

                    val form = GameFormState(
                        nombre = nombre,
                        plataforma = plataforma,
                        horas = horas,
                        rating = rating,
                        comentario = comentario,
                        estado = estado
                    )

                    val result = GameFormValidator.validate(form)

                    errors = result.errors

                    if (result.isValid) {
                        println("Formulario correcto")
                    }


            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Guardar")

        }

    }

}