import java.util.Properties

val propertiesFile = File(rootProject.projectDir, "local.properties")
if (propertiesFile.exists().not()) {
    propertiesFile.createNewFile()
}
val properties = Properties().apply {
     load(propertiesFile.inputStream())
}
val useAARForDevBuild = properties["useAARForDevBuild"] == "true"
val devModules = properties["devModules"]?.toString()?.split(" ").orEmpty()

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Growing"

include(":app")
includeIfEnabled(":feature:second")
includeIfEnabled(":feature:third")
includeIfEnabled(":libraries:designsystem")
includeIfEnabled(":libraries:featuretoggle:impl")
includeIfEnabled(":libraries:featuretoggle:api")

fun includeIfEnabled(name: String, path: String? = null) {
    if (useAARForDevBuild) {
        if (devModules.contains(name)) {
            includeAlways(name, path)
        }
    } else {
        includeAlways(name, path)
    }
}

fun includeAlways(name: String, path: String? = null) {
    include(name)
    path?.let { libraryPath ->
        project(name).projectDir = File(rootDir, libraryPath)
    }
}