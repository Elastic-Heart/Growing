plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
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
        testImplementation(junit)
        androidTestImplementation(androidx.test.junit)
        androidTestImplementation(androidx.test.espresso)
        androidTestImplementation(platform(androidx.compose.bom))
        androidTestImplementation(androidx.compose.ui.test.junit4)
        debugImplementation(androidx.compose.ui.tooling)
        debugImplementation(androidx.compose.ui.test.manifest)

        implementation(project(":feature:Second"))
        implementation(project(":feature:third"))
        implementation(project(":libraries:designsystem"))
        implementation(project(":libraries:snackbar"))
    }
}