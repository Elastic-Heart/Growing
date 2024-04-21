package com.martini.growing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martini.designsystem.components.snackbar.SnackBarDispatcher
import com.martini.designsystem.components.snackbar.SnackBarMessage
import com.martini.designsystem.components.snackbar.SnackBarType
import com.martini.designsystem.utils.UiText
import com.martini.featuretoggle.api.Feature
import com.martini.featuretoggle.api.FeatureToggleChecker
import com.martini.featuretoggle.api.RemoteConfigurator
import com.martini.growing.state.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val remoteConfigurator: RemoteConfigurator,
    featureToggleChecker: FeatureToggleChecker,
    private val dispatcher: SnackBarDispatcher = SnackBarDispatcher.shared
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Idle)

    val uiState = _uiState.asStateFlow()

    val showSnackBarEnabled = featureToggleChecker
        .observe(Feature.HOME_SNACKBAR_ENABLED)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = featureToggleChecker(Feature.HOME_SNACKBAR_ENABLED)
        )

    init {
        fetchRemoteConfig()
    }

    fun showSnackBar() {
        dispatcher.invoke(
            SnackBarMessage(
                message = UiText.Text("Hello world!"),
                type = SnackBarType.ERROR
            )
        )
    }

    private fun fetchRemoteConfig() {
        viewModelScope.launch {
            _uiState.update { MainUiState.Loading }
            remoteConfigurator()
            _uiState.update { MainUiState.Loaded }
        }
    }
}