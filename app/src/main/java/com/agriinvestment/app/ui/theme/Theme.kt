package com.agriinvestment.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

private val AgriLightColorScheme = lightColorScheme(
    primary = ForestGreen,
    onPrimary = White,
    primaryContainer = LightGreen,
    onPrimaryContainer = White,
    secondary = Gold,
    onSecondary = TextPrimary,
    background = LightGray,
    onBackground = TextPrimary,
    surface = White,
    onSurface = TextPrimary,
    error = ErrorRed,
    onError = White,
    outline = Divider
)

// Grands coins arrondis pour les cartes, comme spécifié dans la charte UX.
val AgriShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(28.dp),
    extraLarge = RoundedCornerShape(36.dp)
)

@Composable
fun AgriInvestmentTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = AgriLightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as? android.app.Activity)?.window
        window?.let {
            it.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(it, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AgriShapes,
        content = content
    )
}
