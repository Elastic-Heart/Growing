import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class CommonApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("maven-publish")

        extensions.create("commonAndroidLibrary", CommonAndroidLibraryExtension::class.java)

        val libraryExtension = extensions.getByType<ApplicationExtension>()

        configureCompose(libraryExtension)

        applyCommonAndroidDependencies()

        applyProjectDependencies()
    }
}