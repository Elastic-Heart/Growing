import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.register

class GithubPublishing : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        pluginManager.apply("maven-publish")
    }
}