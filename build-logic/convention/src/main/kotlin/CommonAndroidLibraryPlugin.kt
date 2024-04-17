import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CommonAndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        configureAndroidLibrary()

        dependencies {
            implementation(libs.findLibrary("androidx-ktx").get())
            implementation(libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
            androidTestImplementation(libs.findLibrary("androidx-test-junit").get())
            androidTestImplementation(libs.findLibrary("androidx-test-espresso").get())
        }
    }
}