package com.martini.featuretoggle

import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.martini.featuretoggle.api.Feature
import com.martini.featuretoggle.api.FeatureToggleChecker
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class FirebaseFeatureToggleChecker(
    private val config: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
) : FeatureToggleChecker {
    override fun invoke(feature: Feature): Boolean {
        return config.getBoolean(feature.featureName)
    }

    override fun observe(feature: Feature): Flow<Boolean> {
        return observeConfigUpdate()
            .filter { it.contains(feature.featureName) }
            .map { invoke(feature) }
    }

    private fun observeConfigUpdate() = callbackFlow {
        config.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                config.activate().addOnCompleteListener {
                    trySend(configUpdate.updatedKeys)
                }
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                close(error.cause)
            }
        })
        awaitClose {
            //No cleanup needed
        }
    }
}