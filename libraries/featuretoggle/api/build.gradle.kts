import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.read.properties.plugin)
    `maven-publish`
}

val readProperties = extensions.getByType(ReadPropertiesExtension::class.java)

println("Michael Jackson: ${readProperties.useAARForDevBuild}")

publishing {
    publications {
        register<MavenPublication>("aar") {
            groupId = "io.townsq.app"
            artifactId = project.name
            version = "1.0"
        }
    }
    repositories {
        maven {
            name = "local"
            url = uri("${project.rootDir}/localMavenRepository")
        }
        tasks.named("publishAarPublicationToLocalRepository").dependsOn("assembleDebug")
    }
}

android {
    namespace = "com.martini.featuretoggle.api"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}