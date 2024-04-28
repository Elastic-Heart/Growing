package com.martini.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object GrowingColors {
    val TextPrimary: Color
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme.onSurface
    val TextSecondary: Color
        @Composable
        get() = GrowingPalette.Petrol
    val TextTertiary: Color
        @Composable
        get() = GrowingPalette.Tree
    val Primary: Color
        @Composable
        get() = GrowingPalette.Monstera
    val Error: Color
        @Composable
        get() = GrowingPalette.Blud
    val Secondary: Color
        @Composable
        get() = GrowingPalette.Acerola
    val Tertiary: Color
        @Composable
        get() = GrowingPalette.Pilea
    val DisabledContainer: Color
        @Composable
        get() = GrowingPalette.Odio
    val DisabledContent: Color
        @Composable
        get() = GrowingPalette.Tree
    val Link: Color
        @Composable
        get() = Color.Blue
}

@Composable
fun GrowingColors.toMaterial3Colors() : ColorScheme {
    return MaterialTheme.colorScheme.copy(
        primary = Primary,
        secondary = Secondary,
        tertiary = Tertiary,
        error = Error
    )
}

internal val LocalColors = staticCompositionLocalOf { GrowingColors }