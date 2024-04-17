import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import java.util.Optional

internal fun DependencyHandlerScope.implementation(library: Provider<MinimalExternalModuleDependency>) {
    add("implementation", library)
}

internal fun DependencyHandlerScope.implementation(dependency: Dependency) {
    add("implementation", dependency)
}

internal fun DependencyHandlerScope.testImplementation(library: Provider<MinimalExternalModuleDependency>) {
    add("testImplementation", library)
}

internal fun DependencyHandlerScope.androidTestImplementation(library: Provider<MinimalExternalModuleDependency>) {
    add("androidTestImplementation", library)
}

internal fun DependencyHandlerScope.androidTestImplementation(dependency: Dependency) {
    add("androidTestImplementation", dependency)
}

internal fun DependencyHandlerScope.debugImplementation(library: Provider<MinimalExternalModuleDependency>) {
    add("debugImplementation", library)
}