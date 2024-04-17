import BuildLogicConstants.COMPILE_SDK
import BuildLogicConstants.JAVA_VERSION
import BuildLogicConstants.MINIMUM_SDK
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal val Project.libs
    get() = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

internal fun Project.configureJavaVersion() {
    extensions.configure(JavaPluginExtension::class.java) {
        sourceCompatibility = JAVA_VERSION
        targetCompatibility = JAVA_VERSION
    }
}

internal fun Project.configureKotlinVersion() {
    tasks.withType(KotlinCompile::class.java) {
        kotlinOptions {
            jvmTarget = JAVA_VERSION.toString()
        }
    }
}

internal fun Project.configurePackaging() {
    tasks.withType(KotlinCompile::class.java) {
        excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

internal fun Project.configureDefaultConfig() {
    extensions.getByType(LibraryExtension::class.java).apply {

        compileSdk = COMPILE_SDK

        defaultConfig {

            minSdk = MINIMUM_SDK

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            vectorDrawables {
                useSupportLibrary = true
            }
        }
    }
}

internal fun Project.configureAndroidLibrary() {
    configureJavaVersion()
    configureKotlinVersion()
    configurePackaging()
    configureDefaultConfig()
}