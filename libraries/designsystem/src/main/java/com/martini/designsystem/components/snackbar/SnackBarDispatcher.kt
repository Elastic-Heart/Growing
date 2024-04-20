package com.martini.designsystem.components.snackbar

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SnackBarDispatcher private constructor(){

    companion object {
        val shared: SnackBarDispatcher by lazy {
            SnackBarDispatcher()
        }
    }

    private val _state = MutableStateFlow<SnackBarAction>(SnackBarAction.Hide)

    val state = _state.asStateFlow()

    operator fun invoke(message: SnackBarMessage) {
        _state.update { SnackBarAction.Show(message) }
    }

    fun reset() {
        _state.update { SnackBarAction.Hide }
    }
}