package com.martini.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun GrowingTheme(
    typography: GrowingTypography = GrowingTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTypography provides typography
    ) {
        MaterialTheme(
            typography = typography.toMaterial3Typography()
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
}