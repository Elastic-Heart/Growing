plugins {
    with(libs.plugins) {
        alias(android.library)
        alias(kotlin.android)
        alias(compose.library)
        alias(common.android.library)
    }
}

android {
    namespace = "com.martini.growing.third"
}

commonAndroidLibrary {
    projectDependencies = mapOf(
        ":libraries:designsystem" to "1.0"
    )
}