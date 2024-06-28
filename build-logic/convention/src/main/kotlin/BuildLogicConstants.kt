import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal object BuildLogicConstants {
    const val MINIMUM_SDK = 26
    const val COMPILE_SDK = 34
    val JAVA_VERSION = JavaVersion.VERSION_11
    val JAVA_JVM_TARGET = JvmTarget.JVM_11
    const val PUBLISHING_GROUP_ID = "com.martini.growing"
    const val PUBLISHING_FOLDER = "com/martini/growing"
    const val MAVEN_REPOSITORY_FOLDER = "localMavenRepository"
    const val PUBLISHING_VERSION = "1.0"
}