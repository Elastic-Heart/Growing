import BuildLogicConstants.COMPILE_SDK
import BuildLogicConstants.JAVA_VERSION
import BuildLogicConstants.MAVEN_REPOSITORY_FOLDER
import BuildLogicConstants.MINIMUM_SDK
import BuildLogicConstants.PUBLISHING_FOLDER
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File
import java.util.Properties

internal val Project.libs
    get() = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

internal val Project.propertiesFile
    get() = extensions.getByType<ExtraPropertiesExtension>()

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

internal val Project.libraryName
    get() = displayName.split(" ")[1]
        .replace("'", "")
        .replace(":", "")

internal val Project.localProperties: Properties
    get() {
        val propertiesFile = File(rootProject.projectDir, "local.properties")
        if (propertiesFile.exists().not()) {
            propertiesFile.createNewFile()
        }
        return Properties().apply {
            load(propertiesFile.inputStream())
        }
    }

internal fun Project.configurePublishing() {
    val options = extensions.getByType(CommonAndroidLibraryExtension::class.java)

    afterEvaluate {
        extensions.getByType(PublishingExtension::class.java).apply {
            publications {

                val libraryName = project.libraryName

                register<MavenPublication>("aar") {
                    groupId = BuildLogicConstants.PUBLISHING_GROUP_ID
                    artifactId = libraryName
                    version = options.version
                    artifact("$buildDir/outputs/aar/${project.name}-debug.aar")
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
    }
}

internal fun Project.applyProjectDependencies() {
    val options = extensions.getByType(CommonAndroidLibraryExtension::class.java)

    afterEvaluate {
        val useAARForDevBuild = localProperties["useAARForDevBuild"] == "true"
        val devModules = localProperties["devModules"]?.toString()?.split(" ") ?: emptyList()

        dependencies {
            for (dependencyMap in options.dependencies) {

                val dependency = dependencyMap.key.libraryName
                val dependencyVersion = dependencyMap.value

                if (useAARForDevBuild && devModules.contains(dependency).not()) {
                    val repoAndGroupId = "../$MAVEN_REPOSITORY_FOLDER/$PUBLISHING_FOLDER"
                    val aarFile = files("$repoAndGroupId/$dependency/$dependencyVersion/$dependency-$dependencyVersion.aar")
                    println("Michael Jackson: $aarFile")
                    add("implementation", aarFile)
                } else {
                    add("implementation", project(dependency))
                }
            }
        }
    }
}

internal fun Project.configureRelease() {
    extensions.getByType(LibraryExtension::class.java).apply {
        buildTypes {
            release {
                isMinifyEnabled = true
            }
        }
    }
}

internal fun Project.configureAndroidLibrary() {
    extensions.create("commonAndroidLibrary", CommonAndroidLibraryExtension::class.java)

    configureJavaVersion()
    configureKotlinVersion()
    configurePackaging()
    configureRelease()
    configureDefaultConfig()
    configurePublishing()
    applyProjectDependencies()
}