package com.martini.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun GrowingTheme(
    typography: GrowingTypography = GrowingTypography,
    colors: GrowingColors = GrowingColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalColors provides colors
    ) {
        MaterialTheme(
            typography = typography.toMaterial3Typography(),
            colorScheme = colors.toMaterial3Colors()
        ) {
            content()
        }
    }
}

object GrowingTheme {
    val typography: GrowingTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current
    val colors: GrowingColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current
}