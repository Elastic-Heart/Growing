package com.martini.growing.state

sealed interface MainUiState {
    data object Idle : MainUiState
    data object Loading : MainUiState
    data object Loaded: MainUiState
}