package com.example.nexora.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = LimeGreen,
    onPrimary = Black,
    background = Black,
    onBackground = White,
    surface = DarkOlive,
    onSurface = White
)

private val LightColorScheme = lightColorScheme(
    primary = LimeGreen,
    onPrimary = Black,
    background = White,
    onBackground = Black,
    surface = LightGray,
    onSurface = Black
)

@Composable
fun NexoraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
