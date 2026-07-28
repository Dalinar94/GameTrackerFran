package com.fran.gametrackerdefran.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.fran.gametrackerdefran.ui.components.AppTopBar
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import com.fran.gametrackerdefran.utils.backup.BackupManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    gameViewModel: GameViewModel

) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var showImportDialog by remember {
        mutableStateOf(false)
    }
    var showAboutDialog by remember {
        mutableStateOf(false)
    }
    val exportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/json")
    ) { uri ->

        if (uri != null) {

            val json = gameViewModel.exportGamesToJson()

            BackupManager.saveToUri(
                context = context,
                uri = uri,
                json = json
            )
            scope.launch {
                snackbarHostState.showSnackbar(
                    "Copia de seguridad creada correctamente."
                )
            }
        }
    }
    val importLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->

        if (uri != null) {
            try {

                val json = BackupManager.readJsonFromUri(
                    context = context,
                    uri = uri
                )

                val games = BackupManager.importFromJson(json)

                gameViewModel.replaceAllGames(games)

                navController.popBackStack()

            } catch (e: Exception) {

                scope.launch {
                    snackbarHostState.showSnackbar(
                        "No se ha podido importar la copia de seguridad."
                    )
                }
            }
        }
    }
    Scaffold(

        topBar = {
            AppTopBar(
                title = "Ajustes",
                showBackButton = true,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text(
                text = "Biblioteca",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                )
            )
            HorizontalDivider()

            ListItem(
                headlineContent = {
                    Text("Exportar biblioteca")
                },
                supportingContent = {
                    Text("Guarda una copia de seguridad de tus juegos.")
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Upload,
                        contentDescription = null
                    )
                },
                modifier = Modifier.clickable {

                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm")

                    val fileName = "gametracker_backup_${LocalDateTime.now().format(formatter)}.json"

                    exportLauncher.launch(fileName)

                }
            )

            HorizontalDivider()

            ListItem(
                headlineContent = {
                    Text("Importar biblioteca")
                },
                supportingContent = {
                    Text("Restaura una copia de seguridad.")
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = null
                    )
                },
                modifier = Modifier.clickable {
                    showImportDialog = true
                }
            )
            Text(
                text = "Información",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                )
            )
            HorizontalDivider()

            ListItem(
                headlineContent = {
                    Text("Acerca de")
                },
                supportingContent = {
                    Text("Información de la aplicación")
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null
                    )
                },
                modifier = Modifier.clickable {
                    showAboutDialog = true
                }
            )

        }
        if (showImportDialog) {

            AlertDialog(
                onDismissRequest = {
                    showImportDialog = false
                },
                title = {
                    Text("Restaurar copia de seguridad")
                },
                text = {
                    Text(
                        "La biblioteca actual será reemplazada por el contenido del archivo seleccionado.\n\nEsta acción no se puede deshacer."
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {

                            showImportDialog = false

                            importLauncher.launch(
                                arrayOf("application/json")
                            )

                        }
                    ) {
                        Text("Restaurar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showImportDialog = false
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )

        }
        if (showAboutDialog) {

            AlertDialog(
                onDismissRequest = {
                    showAboutDialog = false
                },
                title = {
                    Text("GameTracker")
                },
                text = {
                    Text(
                        "Versión 1.0\n\n" +
                                "Desarrollado por Fran.\n\n" +
                                "Aplicación para gestionar tu colección de videojuegos."
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showAboutDialog = false
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            )

        }
    }
}