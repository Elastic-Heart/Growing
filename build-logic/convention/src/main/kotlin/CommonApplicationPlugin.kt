import org.gradle.api.Plugin
import org.gradle.api.Project

class CommonApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("maven-publish")

        extensions.create("commonAndroidLibrary", CommonAndroidLibraryExtension::class.java)

        applyProjectDependencies()
    }
}