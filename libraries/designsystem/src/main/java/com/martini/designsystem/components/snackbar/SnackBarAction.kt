package com.martini.designsystem.components.snackbar

import androidx.compose.material3.SnackbarDuration
import com.martini.designsystem.utils.UiText

sealed interface SnackBarAction {
    data object Hide : SnackBarAction
    data class Show(
        val message: SnackBarMessage
    ) : SnackBarAction
}

data class SnackBarMessage(
    val message: UiText,
    val type: SnackBarType,
    val duration: SnackbarDuration = SnackbarDuration.Short,
    val actionLabel: UiText? = null,
    val withDismissAction: Boolean = false
)