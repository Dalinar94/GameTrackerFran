package com.fran.gametrackerdefran.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(

    primary = Primary,
    onPrimary = OnPrimary,

    secondary = Secondary,
    onSecondary = OnSecondary,

    background = Background,
    surface = Surface,
    surfaceVariant = SurfaceVariant,

    outline = Outline

)

private val DarkColorScheme = darkColorScheme(

    primary = Primary,
    secondary = Secondary

)

@Composable
fun GameTrackerDeFranTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme =
        if (darkTheme)
            DarkColorScheme
        else
            LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

}