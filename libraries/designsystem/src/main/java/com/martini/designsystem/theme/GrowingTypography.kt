package com.martini.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object GrowingTypography {
    val h0: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )
    val h1: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 29.sp
        )
    val h2: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    val h3: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    val h4: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )
    val h5: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp
        )
    val body: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 21.sp
        )
    val button: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 21.sp
        )
    val smallCaption: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 19.sp
    )
}

@Composable
internal fun GrowingTypography.toMaterial3Typography(): Typography {
    return Typography(
        bodyLarge = body,
        titleLarge = h1,
        titleMedium = h2,
        titleSmall = h3,
        labelSmall = smallCaption
    )
}

internal val LocalTypography = staticCompositionLocalOf { GrowingTypography }