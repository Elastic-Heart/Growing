package com.martini.featuretoggle.api

interface RemoteConfigurator {
    suspend operator fun invoke()
}