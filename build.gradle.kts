// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    with(libs.plugins) {
        alias(android.application) apply false
        alias(compose.compiler) apply false
        alias(common.android.library) apply false
        alias(common.android.application) apply false
        alias(android.library) apply false
        alias(compose.library) apply false
        alias(kotlin.android) apply false
        alias(com.google.gms.services) apply false
        alias(read.properties.plugin) apply false
        alias(github.publishing) apply false
        alias(kotlin.serialization) apply false
    }
}