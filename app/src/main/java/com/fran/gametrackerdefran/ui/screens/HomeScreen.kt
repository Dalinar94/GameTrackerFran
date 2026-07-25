package com.fran.gametrackerdefran.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.fran.gametrackerdefran.ui.components.AppTopBar
import com.fran.gametrackerdefran.ui.components.GameCard
import com.fran.gametrackerdefran.ui.navigation.Screen
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel
import com.fran.gametrackerdefran.ui.components.EmptyGames
import com.fran.gametrackerdefran.ui.components.StatusFilterBar
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.fran.gametrackerdefran.ui.components.SearchBar
import com.fran.gametrackerdefran.ui.components.SortMenu
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen( navController: NavController,
                gameViewModel: GameViewModel) {
    val games by gameViewModel.games.collectAsState()

    Scaffold(

        topBar = {
            AppTopBar(
                title = "GameTracker",
                actions = {

                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Statistics.route)
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.BarChart,
                            contentDescription = "Estadísticas"
                        )

                    }

                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddGame.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir juego"
                )
            }
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            item {

                SearchBar(
                    query = gameViewModel.searchQuery,
                    onQueryChange = gameViewModel::updateSearchQuery
                )

            }
            item {
                SortMenu(
                    selectedSort = gameViewModel.selectedSort,
                    onSortSelected = gameViewModel::setSort
                )
            }
            item {

                StatusFilterBar(
                    selectedFilter = gameViewModel.selectedFilter,
                    onFilterSelected = gameViewModel::setFilter
                )

            }

            if (games.isEmpty()) {

                item {
                    EmptyGames()
                }

            } else {

                items(gameViewModel.filteredGames) { juego ->
                    GameCard(
                        game = juego,
                        onClick = {
                            navController.navigate(
                                Screen.EditGame.createRoute(juego.id)
                            )
                        }
                    )

                }

            }

        }

    }

}