plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.common.android.application)
    alias(libs.plugins.com.google.gms.services)
    alias(libs.plugins.com.google.firebase.crashlytics)
}

android {
    namespace = "com.martini.growing"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.martini.growing"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinComposeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    with(libs) {
        implementation(androidx.ktx)
        implementation(androidx.lifecycle.runtime.ktx)
        implementation(androidx.compose.activity)
        implementation(platform(androidx.compose.bom))
        implementation(androidx.compose.ui)
        implementation(androidx.compose.ui.graphics)
        implementation(androidx.compose.ui.tooling.preview)
        implementation(androidx.compose.material)
        implementation(androidx.navigation.compose)
        implementation(androidx.lifecycle.runtime.compose)
        implementation(libs.bundles.koin)
        implementation(platform(libs.firebase.bom))
        implementation(libs.bundles.firebase)
        testImplementation(junit)
        androidTestImplementation(androidx.test.junit)
        androidTestImplementation(androidx.test.espresso)
        androidTestImplementation(platform(androidx.compose.bom))
        androidTestImplementation(androidx.compose.ui.test.junit4)
        debugImplementation(androidx.compose.ui.tooling)
        debugImplementation(androidx.compose.ui.test.manifest)
    }
}

commonAndroidLibrary {
    projectDependencies = mapOf(
        ":libraries:featuretoggle:impl" to "1.0",
        ":libraries:featuretoggle:api" to "1.0",
        ":feature:second" to "0.0.1",
        ":feature:third" to "1.0",
        ":libraries:designsystem" to "1.0",
    )
}