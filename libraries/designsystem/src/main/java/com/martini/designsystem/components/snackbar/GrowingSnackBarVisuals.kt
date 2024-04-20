package com.martini.designsystem.components.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

data class GrowingSnackBarVisuals(
    override val actionLabel: String? = null,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    override val message: String,
    override val withDismissAction: Boolean = false,
    val type: SnackBarType
) : SnackbarVisuals