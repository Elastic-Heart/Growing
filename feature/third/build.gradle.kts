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

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }

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