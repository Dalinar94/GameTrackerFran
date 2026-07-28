package com.fran.gametrackerdefran

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fran.gametrackerdefran.data.GameRepository
import com.fran.gametrackerdefran.data.local.GameDatabase
import com.fran.gametrackerdefran.ui.navigation.AppNavigation
import com.fran.gametrackerdefran.ui.theme.GameTrackerDeFranTheme
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModel
import com.fran.gametrackerdefran.ui.viewmodel.GameViewModelFactory
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.fran.gametrackerdefran.data.repository.WishlistRepository
import com.fran.gametrackerdefran.data.repository.RawgRepository
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()   // ✅ Aquí sí

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            val database = GameDatabase.getDatabase(applicationContext)
            val repository = GameRepository(database.gameDao())
            val wishlistRepository = WishlistRepository(database.wishlistDao())
            val rawgRepository = RawgRepository()
            val factory = GameViewModelFactory(
                repository,
                wishlistRepository,
                rawgRepository
            )

            val gameViewModel: GameViewModel = viewModel(
                factory = factory
            )

            GameTrackerDeFranTheme {
                AppNavigation(gameViewModel)
            }
        }
    }
}