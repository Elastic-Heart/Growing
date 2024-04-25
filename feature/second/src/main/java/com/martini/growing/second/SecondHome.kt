package com.martini.growing.second

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.martini.designsystem.components.snackbar.GrowingSnackBarHost
import com.martini.designsystem.components.snackbar.SnackBarDispatcher
import com.martini.designsystem.components.snackbar.SnackBarMessage
import com.martini.designsystem.components.snackbar.SnackBarType
import com.martini.designsystem.utils.UiText

@Composable
fun SecondHome(
    dispatcher: SnackBarDispatcher = SnackBarDispatcher.shared
) {
    Scaffold(
        snackbarHost = { GrowingSnackBarHost() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            TextButton(
                onClick = {
                    dispatcher.invoke(
                        SnackBarMessage(
                            message = UiText.Resource(R.string.app_name),
                            type = SnackBarType.ERROR
                        )
                    )
                }
            ) {
                Text(text = "Second home")
            }
        }
    }
}