import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class CommonAndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("maven-publish")
        configureAndroidLibrary()

        applyCommonAndroidDependencies()

        val libraryExtension = extensions.getByType<LibraryExtension>()

        configureCompose(libraryExtension)
    }
}