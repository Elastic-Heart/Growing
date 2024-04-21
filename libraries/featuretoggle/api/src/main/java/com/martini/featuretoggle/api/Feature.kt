package com.martini.featuretoggle.api

enum class Feature(val featureName: String, val enabledByDefault: Boolean) {
    HOME_SNACKBAR_ENABLED("home_snackbar_enabled", false)
}