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
import com.martini.snackbar.GrowingSnackBarHost
import com.martini.snackbar.SnackBarDispatcher
import com.martini.snackbar.SnackBarMessage
import com.martini.snackbar.SnackBarType

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
            TextButton(onClick = { dispatcher.invoke(getMessage()) }) {
                Text(text = "Second home")
            }
        }
    }
}

private fun getMessage(): SecondHomeMessage {
    return SecondHomeMessage()
}

data class SecondHomeMessage(
    override val id: Int = R.string.app_name,
    override val type: SnackBarType = listOf(SnackBarType.ERROR, SnackBarType.SUCCESS).random()
) : SnackBarMessage