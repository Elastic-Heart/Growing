package com.martini.featuretoggle

import kotlinx.coroutines.tasks.await
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.martini.featuretoggle.api.Feature
import com.martini.featuretoggle.api.RemoteConfigurator
import com.martini.featuretoggle.impl.BuildConfig

class FirebaseRemoteConfigurator(
    private val config: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
) : RemoteConfigurator {

    companion object {
        private const val PROD_FETCH_INTERVAL = 3600L
        private const val DEBUG_FETCH_INTERVAL = 10L
    }

    override suspend operator fun invoke() {
        val settings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) {
                DEBUG_FETCH_INTERVAL
            } else {
                PROD_FETCH_INTERVAL
            }
        }
        config.setConfigSettingsAsync(settings).await()
        config.setDefaultsAsync(getDefaults())
        config.fetchAndActivate().await()
    }

    private fun getDefaults(): Map<String, Boolean> {
        return Feature.entries.associate { feature ->
            feature.featureName to feature.enabledByDefault
        }
    }
}