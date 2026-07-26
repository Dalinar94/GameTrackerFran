package com.fran.gametrackerdefran.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fran.gametrackerdefran.data.model.Platform

@Composable
fun LibraryIcon(
    library: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = library
) {

    val platform = Platform.entries.find {
        it.displayName == library
    } ?: Platform.STEAM

    Icon(
        painter = painterResource(platform.icon),
        contentDescription = contentDescription,
        modifier = modifier.size(22.dp),
        tint = Color.Unspecified
    )
}