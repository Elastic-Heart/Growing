package com.martini.featuretoggle.di

import com.martini.featuretoggle.FirebaseFeatureToggleChecker
import com.martini.featuretoggle.FirebaseRemoteConfigurator
import com.martini.featuretoggle.api.FeatureToggleChecker
import com.martini.featuretoggle.api.RemoteConfigurator
import org.koin.dsl.module

val FeatureToggleModule = module {
    factory<RemoteConfigurator> { FirebaseRemoteConfigurator() }
    factory<FeatureToggleChecker> { FirebaseFeatureToggleChecker() }
}