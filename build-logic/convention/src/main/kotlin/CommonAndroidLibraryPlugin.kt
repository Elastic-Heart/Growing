import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CommonAndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("maven-publish")
        configureAndroidLibrary()

        dependencies {
            implementation(libs.findLibrary("androidx-ktx").get())
            implementation(libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
            testImplementation(libs.findLibrary("junit").get())
            testImplementation(libs.findLibrary("androidx-test-core").get())
            testImplementation(libs.findLibrary("io-mockk").get())
            androidTestImplementation(libs.findLibrary("androidx-test-junit").get())
            androidTestImplementation(libs.findLibrary("androidx-test-espresso").get())
        }
    }
}