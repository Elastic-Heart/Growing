package com.martini.snackbar

import androidx.lifecycle.ViewModel

class SnackBarViewModel(
    private val dispatcher: SnackBarDispatcher = SnackBarDispatcher.shared
) : ViewModel() {
    val state = dispatcher.state

    fun reset() {
        dispatcher.reset()
    }
}