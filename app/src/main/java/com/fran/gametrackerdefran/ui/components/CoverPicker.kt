package com.fran.gametrackerdefran.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fran.gametrackerdefran.ui.theme.GTRadius
import coil.compose.AsyncImage
@Composable
fun CoverPicker(
    imageUri: String,
    onImageSelected: (String) -> Unit
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->

        uri?.let {
            onImageSelected(it.toString())
        }

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .width(120.dp)
                .height(180.dp),
            shape = RoundedCornerShape(GTRadius.Medium)
        ) {

            if (imageUri.isNotBlank()) {

                AsyncImage(
                    model = imageUri,
                    contentDescription = "Portada",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

            } else {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.SportsEsports,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )

                }

            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                launcher.launch("image/*")
            }
        ) {
            Text("Elegir imagen")
        }

    }

}