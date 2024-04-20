package com.martini.designsystem.components.snackbar

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
class GrowingSnackBarHostState(
    val hostState: SnackbarHostState
) {

    suspend fun showSnackbar(
        visuals: GrowingSnackBarVisuals
    ): SnackbarResult {
        return hostState.showSnackbar(visuals)
    }
}

@Composable
fun rememberGrowingSnackbarHostState(
    hostState: SnackbarHostState = remember { SnackbarHostState() }
) = remember {
    GrowingSnackBarHostState(hostState)
}