package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameForm() {

    var nombre by remember { mutableStateOf("") }
    var plataforma by remember { mutableStateOf("") }
    var horas by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }
    var comentario by remember { mutableStateOf("") }

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
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = plataforma,
            onValueChange = { plataforma = it },
            label = { Text("Plataforma") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = horas,
            onValueChange = { horas = it },
            label = { Text("Horas") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Valoración")

        StarRating(

            rating = rating,

            onRatingChanged = {

                rating = it

            }

        )

        OutlinedTextField(
            value = comentario,
            onValueChange = { comentario = it },
            label = { Text("Comentario") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Guardar")

        }

    }

}