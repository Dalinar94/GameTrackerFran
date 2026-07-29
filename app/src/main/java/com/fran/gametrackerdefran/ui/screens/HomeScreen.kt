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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.fran.gametrackerdefran.ui.components.SearchBar
import com.fran.gametrackerdefran.ui.components.SortMenu
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.filled.GridView
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import com.fran.gametrackerdefran.ui.components.GameGridCard
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen( navController: NavController,
                gameViewModel: GameViewModel) {
    val games by gameViewModel.games.collectAsState()
    var isGridView by remember { mutableStateOf(false) }
    var visibleGames by remember { mutableIntStateOf(20) }
    val listState = rememberLazyListState()
    LaunchedEffect(
        listState.firstVisibleItemIndex,
        gameViewModel.filteredGames.size
    ) {

        val lastVisible =
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return@LaunchedEffect

        if (
            lastVisible >= visibleGames - 1 &&
            visibleGames < gameViewModel.filteredGames.size
        ) {
            visibleGames += 20
        }

    }
    Scaffold(
        topBar = {
            AppTopBar(
                title = "GameTracker",
                actions = {

                    IconButton(
                        onClick = {
                            isGridView = !isGridView
                        }
                    ) {
                        Icon(
                            imageVector = if (isGridView) {
                                Icons.Default.ViewList
                            } else {
                                Icons.Default.GridView
                            },
                            contentDescription = "Cambiar vista"
                        )
                    }

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
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Wishlist.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Lista de deseos"
                        )
                    }
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Settings.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Ajustes"
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
        if (isGridView) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(padding)
            ) {

                item(span = { GridItemSpan(2) }) {
                    SearchBar(
                        query = gameViewModel.searchQuery,
                        onQueryChange = gameViewModel::updateSearchQuery
                    )
                }

                item(span = { GridItemSpan(2) }) {
                    SortMenu(
                        selectedSort = gameViewModel.selectedSort,
                        onSortSelected = gameViewModel::setSort
                    )
                }

                item(span = { GridItemSpan(2) }) {
                    StatusFilterBar(
                        selectedFilter = gameViewModel.selectedFilter,
                        onFilterSelected = gameViewModel::setFilter
                    )
                }

                if (games.isEmpty()) {

                    item(span = { GridItemSpan(2) }) {
                        EmptyGames()
                    }

                } else {

                    items(gameViewModel.filteredGames) { juego ->

                        GameGridCard(
                            game = juego,
                            onClick = {
                                navController.navigate(
                                    Screen.GameDetail.createRoute(juego.id)
                                )
                            },
                            onFavoriteClick = {
                                gameViewModel.toggleFavorite(juego)
                            }
                        )

                    }

                }

            }

        } else {
            LazyColumn(
                state = listState,
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

                    items(
                        gameViewModel.filteredGames.take(visibleGames)
                    ) { juego ->
                        GameCard(
                            game = juego,
                            onClick = {
                                navController.navigate(
                                    Screen.GameDetail.createRoute(juego.id)
                                )
                            },
                            onFavoriteClick = {
                                gameViewModel.toggleFavorite(juego)
                            }
                        )

                    }

                }

            }

        }
    }
}