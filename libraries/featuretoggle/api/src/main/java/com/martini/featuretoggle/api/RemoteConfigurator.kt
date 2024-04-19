package com.martini.featuretoggle.api

fun interface RemoteConfigurator {
    suspend operator fun invoke()
}