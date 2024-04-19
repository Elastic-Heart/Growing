// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    with(libs.plugins) {
        alias(android.application) apply false
        alias(android.library) apply false
        alias(kotlin.android) apply false
        alias(com.google.gms.services) apply false
        alias(com.google.firebase.crashlytics) apply false
    }
}