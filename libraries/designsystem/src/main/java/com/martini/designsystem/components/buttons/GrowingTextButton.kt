package com.martini.designsystem.components.buttons

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.martini.designsystem.theme.GrowingTheme

@Composable
fun GrowingTextButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    TextButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors().copy(
            disabledContainerColor = GrowingTheme.colors.DisabledContainer,
            disabledContentColor = GrowingTheme.colors.DisabledContent,
        ),
        content = content
    )
}