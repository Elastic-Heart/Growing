plugins {
    `kotlin-dsl`
}

dependencies {
    with(libs) {
        compileOnly(kotlin.gradlePlugin)
        compileOnly(android.gradlePlugin)
    }
}

gradlePlugin {
    plugins {
        register("composePlugin") {
            id = "compose.plugin"
            implementationClass = "ComposePlugin"
        }
        register("commonAndroidLibraryPlugin") {
            id = "common.android.library.plugin"
            implementationClass = "CommonAndroidLibraryPlugin"
        }
        register("commonAndroidApplicationPlugin") {
            id = "common.android.application.plugin"
            implementationClass = "CommonApplicationPlugin"
        }
        register("githubPublishing") {
            id = "github.publishing"
            implementationClass = "GithubPublishing"
        }
        register("readPropertiesPlugin") {
            id = "read.properties.plugin"
            implementationClass = "ReadPropertiesPlugin"
        }
    }
}
