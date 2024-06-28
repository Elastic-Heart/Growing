import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class CommonApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        val composeCompilerPlugin = libs.findPlugin("compose-compiler").get().get()
        pluginManager.apply(composeCompilerPlugin.pluginId)
        pluginManager.apply("maven-publish")

        extensions.create("commonAndroidLibrary", CommonAndroidLibraryExtension::class.java)

        val applicationExtension = extensions.getByType<ApplicationExtension>()

        enableCompose(applicationExtension)
        configureJavaVersion(applicationExtension)
        configureKotlinVersion()

        applyCommonAndroidDependencies()

        applyProjectDependencies()
    }
}