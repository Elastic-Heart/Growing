import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        val extension = extensions.getByType<LibraryExtension>()

        with(extension) {
            buildFeatures { compose = true }
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            val composeBom = platform(bom)
            implementation(composeBom)
            implementation(libs.findLibrary("androidx-compose-ui").get())
            implementation(libs.findLibrary("androidx-compose-ui-graphics").get())
            implementation(libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            implementation(libs.findLibrary("androidx-compose-material").get())
            implementation(libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
            implementation(libs.findLibrary("androidx-lifecycle-runtime-compose").get())
            androidTestImplementation(composeBom)
            androidTestImplementation(libs.findLibrary("androidx-compose-ui-test-junit4").get())
            debugImplementation(libs.findLibrary("androidx-compose-ui-tooling").get())
            debugImplementation(libs.findLibrary("androidx-compose-ui-test-manifest").get())
        }
    }
}