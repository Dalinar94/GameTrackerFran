package com.fran.gametrackerdefran.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fran.gametrackerdefran.ui.components.AppTopBar
import com.fran.gametrackerdefran.ui.components.StatisticCard
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fran.gametrackerdefran.ui.components.DistributionRow
import com.fran.gametrackerdefran.ui.components.PlatformDistributionRow
import com.fran.gametrackerdefran.ui.components.StatisticRow
import com.fran.gametrackerdefran.ui.components.StatisticsSection
import com.fran.gametrackerdefran.ui.model.Statistic
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel
@Composable
fun StatisticsScreen(navController: NavController, gameViewModel: GameViewModel) {
    val statistics = listOf(

        Statistic(
            title = "Juegos totales",
            value = gameViewModel.totalGames.toString()
        ),

        Statistic(
            title = "Completados",
            value = gameViewModel.completedGames.toString()
        ),

        Statistic(
            title = "Jugando",
            value = gameViewModel.playingGames.toString()
        ),

        Statistic(
            title = "Pendientes",
            value = gameViewModel.pendingGames.toString()
        ),

        Statistic(
            title = "Abandonados",
            value = gameViewModel.abandonatedGames.toString()
        ),

        Statistic(
            title = "Valoración media",
            value = String.format("%.1f ⭐", gameViewModel.averageRating)
        ),

        Statistic(
            title = "Completados (%)",
            value = "${gameViewModel.completionPercentage}%"
        )

    )

    Scaffold(

        topBar = {

            AppTopBar(
                title = "Estadísticas",
                showBackButton = true,
                onBackClick = {
                    navController.popBackStack()
                }
            )

        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                StatisticsSection(
                    title = "📊 Resumen"
                ) {

                    statistics.forEach { statistic ->

                        StatisticRow(
                            title = statistic.title,
                            value = statistic.value
                        )

                    }

                }

            }

            item {

                StatisticsSection(
                    title = "📈 Estados"
                ) {

                    DistributionRow(
                        label = "Completados",
                        value = gameViewModel.completedGames
                    )

                    DistributionRow(
                        label = "Jugando",
                        value = gameViewModel.playingGames
                    )

                    DistributionRow(
                        label = "Pendientes",
                        value = gameViewModel.pendingGames
                    )

                    DistributionRow(
                        label = "Abandonados",
                        value = gameViewModel.abandonatedGames
                    )

                }

            }

            item {

                StatisticsSection(
                    title = "🎮 Plataformas"
                ) {

                    gameViewModel.gamesByPlatform.forEach { (platform, count) ->

                        PlatformDistributionRow(
                            platform = platform,
                            value = count,
                            progress = if (gameViewModel.totalGames == 0) {
                                0f
                            } else {
                                count.toFloat() / gameViewModel.totalGames
                            }
                        )

                    }

                }

            }

        }
        }

    }
