package com.martini.designsystem.components.snackbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.martini.designsystem.utils.text
import kotlinx.coroutines.launch

@Composable
fun GrowingSnackBar(
    modifier: Modifier = Modifier,
    data: SnackbarData
) {
    val visuals = data.visuals as GrowingSnackBarVisuals

    Snackbar(
        modifier = modifier.padding(12.dp),
        containerColor = when (visuals.type) {
            SnackBarType.WARNING -> Color.Yellow
            SnackBarType.ERROR -> Color.Red
            SnackBarType.SUCCESS -> Color.Green
        }
    ) {
        Text(text = visuals.message)
    }
}

@Composable
fun GrowingSnackBarHost(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState = remember {
        SnackbarHostState()
    },
    onDismissed: () -> Unit = {},
    onAction: () -> Unit = {}
) {
    val snackBarViewModel: SnackBarViewModel = viewModel()

    val state by snackBarViewModel.state.collectAsStateWithLifecycle()
    val show = (state as? SnackBarAction.Show)?.message
    val message = show?.message?.text

    val coroutineScope = rememberCoroutineScope()

    SnackbarHost(
        modifier = modifier,
        hostState = hostState
    ) {
        GrowingSnackBar(data = it)
    }

    LaunchedEffect(message) {
        message?.let { message ->
            val visuals = GrowingSnackBarVisuals(
                message = message,
                type = show.type
            )
            coroutineScope.launch {
                val result = hostState.showSnackbar(visuals)
                when (result) {
                    SnackbarResult.Dismissed -> onDismissed()
                    SnackbarResult.ActionPerformed -> onAction()
                }
            }
            snackBarViewModel.reset()
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun GrowingSnackBarPreview() {
    GrowingSnackBar(
        data = object : SnackbarData {
            override val visuals: SnackbarVisuals
                get() = object : SnackbarVisuals {
                    override val actionLabel = null
                    override val duration: SnackbarDuration
                        get() = SnackbarDuration.Short
                    override val message = "Hello world!"
                    override val withDismissAction = false
                }

            override fun dismiss() {
                //Empty body since this a preview
            }

            override fun performAction() {
                //Empty body since this a preview
            }
        }
    )
}