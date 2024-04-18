package com.martini.snackbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface SnackBarAction {
    data object None : SnackBarAction
    data class Show(
        val message: SnackBarMessage
    ) : SnackBarAction
}

interface SnackBarMessage {
    val id: Int
    val type: SnackBarType

    val message @Composable
    get() = stringResource(id = id)
}