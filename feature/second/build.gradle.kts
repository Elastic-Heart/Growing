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

dependencies {
    implementation(libs.bundles.kotlinx.serialization)
}

commonAndroidLibrary {
    projectDependencies = mapOf(
        ":libraries:designsystem" to libs.versions.growingVersion.get(),
        ":libraries:networking" to libs.versions.growingVersion.get()
    )
}
