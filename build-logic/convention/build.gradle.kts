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
    }
}
