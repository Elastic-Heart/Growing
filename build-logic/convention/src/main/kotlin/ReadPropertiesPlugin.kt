import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.util.Properties

class ReadPropertiesPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val propertiesFile = File(target.rootProject.projectDir, "local.properties")
        if (propertiesFile.exists().not()) {
            propertiesFile.createNewFile()
        }
        val properties = Properties().apply {
            load(propertiesFile.inputStream())
        }

        val extension = target.extensions.create("readProperties", ReadPropertiesExtension::class.java)

        extension.useAARForDevBuild = properties["useAARForDevBuild"] == "true"
        extension.devModules = properties["devModules"]?.toString()?.split(" ") ?: emptyList()
    }
}