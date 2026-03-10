package com.plcoding.cryptocurrencyappyt.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = ColorPrimary,
    onPrimary = DarkGray,
    background = DarkGray,
    onBackground = TextWhite
)

private val LightColorPalette = lightColorScheme(
    primary = ColorPrimary,
    onPrimary = DarkGray,
    background = Color.White,
    onBackground = MediumGray
)

@Composable
fun CryptocurrencyAppYTTheme(darkTheme: Boolean = true, content: @Composable() () -> Unit) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}