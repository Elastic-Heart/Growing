plugins {
    with(libs.plugins) {
        alias(android.library)
        alias(kotlin.android)
        alias(compose.library)
        alias(common.android.library)
        alias(github.publishing)
    }
}

android {
    namespace = "com.martini.growing.second"
}

commonAndroidLibrary {
    projectDependencies = mapOf(
        ":libraries:designsystem" to "1.0"
    )
    projectVersion = "0.0.2"
}
