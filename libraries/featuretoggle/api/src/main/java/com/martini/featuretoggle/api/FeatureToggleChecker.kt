package com.martini.featuretoggle.api

import kotlinx.coroutines.flow.Flow

interface FeatureToggleChecker {
    operator fun invoke(feature: Feature) : Boolean

    fun observe(feature: Feature) : Flow<Boolean>
}