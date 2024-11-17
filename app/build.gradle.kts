import java.io.FileOutputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.common.android.application)
    alias(libs.plugins.com.google.gms.services)
    alias(libs.plugins.com.google.firebase.crashlytics)
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
if (keystorePropertiesFile.exists().not()) {
    keystorePropertiesFile.createNewFile()

    val defaultProperties = Properties().apply {
        setProperty("storeFile", "")
        setProperty("storePassword", "")
        setProperty("keyAlias", "")
        setProperty("keyPassword", "")
    }

    FileOutputStream(keystorePropertiesFile).use { output ->
        defaultProperties.store(output, "Default Keystore Properties")
    }
}
val keystoreProperties = Properties()
keystoreProperties.load(keystorePropertiesFile.inputStream())

android {
    namespace = "com.martini.growing"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.martini.growing"
        minSdk = 26
        compileSdk = 35
        targetSdk = 35
        versionCode = libs.versions.growingVersionCode.get().toInt()
        versionName = libs.versions.growingVersion.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
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
            signingConfig = signingConfigs.getByName("release")
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("demo") {
            dimension = "version"
            applicationIdSuffix = ".demo"
            versionNameSuffix = "-demo"
        }

        create("prod") {
            dimension = "version"
            applicationIdSuffix = ".prod"
            versionNameSuffix = "-prod"
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
}

dependencies {
    with(libs) {
        implementation(androidx.ktx)
        implementation(androidx.lifecycle.runtime.ktx)
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
        ":libraries:featuretoggle:impl" to projectVersion,
        ":libraries:featuretoggle:api" to projectVersion,
        ":feature:second" to projectVersion,
        ":feature:third" to projectVersion,
        ":libraries:designsystem" to projectVersion,
        ":libraries:networking" to projectVersion
    )
}