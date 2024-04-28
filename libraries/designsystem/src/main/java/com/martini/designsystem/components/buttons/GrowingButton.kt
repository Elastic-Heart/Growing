package com.martini.designsystem.components.buttons

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.martini.designsystem.theme.GrowingTheme

@Composable
fun GrowingButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors().copy(
            disabledContainerColor = GrowingTheme.colors.DisabledContainer,
            disabledContentColor = GrowingTheme.colors.DisabledContent,
            containerColor = GrowingTheme.colors.Primary
        ),
        content = content
    )
}