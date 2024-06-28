import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class CommonAndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        val libraryExtension = extensions.getByType<LibraryExtension>()
        val composeCompilerPlugin = libs.findPlugin("compose-compiler").get().get()

        pluginManager.apply(composeCompilerPlugin.pluginId)
        pluginManager.apply("maven-publish")

        configureAndroidLibrary(libraryExtension)

        applyCommonAndroidDependencies()

        enableCompose(libraryExtension)
    }
}