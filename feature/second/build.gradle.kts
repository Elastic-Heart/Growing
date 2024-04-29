plugins {
    with(libs.plugins) {
        alias(android.library)
        alias(kotlin.android)
        alias(compose.library)
        alias(common.android.library)
        alias(github.publishing)
        alias(kotlin.serialization)
    }
}

android {
    namespace = "com.martini.growing.second"
}

dependencies {
    implementation(libs.bundles.kotlinx.serialization)
}

commonAndroidLibrary {
    projectDependencies = mapOf(
        ":libraries:designsystem" to "1.0",
        ":libraries:networking" to "1.0"
    )
}
