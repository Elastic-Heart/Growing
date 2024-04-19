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
include(":feature:Second")
include(":feature:third")
include(":libraries:designsystem")
include(":libraries:snackbar")
include(":libraries:featuretoggle:impl")
include(":libraries:featuretoggle:api")
