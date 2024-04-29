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

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

commonAndroidLibrary {
    projectDependencies = mapOf(
        ":libraries:designsystem" to projectVersion
    )
}