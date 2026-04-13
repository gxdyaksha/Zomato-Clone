package com.example.zomato.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = ZomatoRed,
    secondary = ZomatoDarkRed,
    background = BackgroundGray,
    surface = CardWhite,
    onPrimary = CardWhite,
    onSecondary = CardWhite,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun ZomatoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}