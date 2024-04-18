plugins {
    with(libs.plugins) {
        alias(android.library)
        alias(kotlin.android)
        alias(compose.library)
        alias(common.android.library)
    }
}

android {
    namespace = "com.martini.growing.second"
}
