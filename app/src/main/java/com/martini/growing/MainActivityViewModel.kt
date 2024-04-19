package com.martini.growing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martini.featuretoggle.api.Feature
import com.martini.featuretoggle.api.FeatureToggleChecker
import com.martini.featuretoggle.api.RemoteConfigurator
import com.martini.growing.state.MainUiState
import com.martini.snackbar.SnackBarDispatcher
import com.martini.snackbar.SnackBarMessage
import com.martini.snackbar.SnackBarType
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
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    init {
        fetchRemoteConfig()
    }

    fun showSnackBar() {
        dispatcher.invoke(MainActivityMessage())
    }

    private fun fetchRemoteConfig() {
        viewModelScope.launch {
            _uiState.update { MainUiState.Loading }
            remoteConfigurator()
            _uiState.update { MainUiState.Loaded }
        }
    }
}

data class MainActivityMessage(
    override val id: Int = R.string.app_name,
    override val type: SnackBarType = listOf(SnackBarType.SUCCESS, SnackBarType.ERROR).random()
) : SnackBarMessage