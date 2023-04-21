import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

internal interface NBTestConventionPlugin : NBConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        plugins {
            applyPlugins()
        }
        dependencies {
            val dependencyNotationPairs = listOf(
                Pair(libs.getLibrary("androidx.test.core"), true),
                Pair(libs.getLibrary("androidx.test.runner"), false),
                Pair(libs.getLibrary("com.google.dagger.hiltAndroidTesting"), true),
                Pair(libs.getLibrary("org.jetbrains.kotlinx.kotlinxCoroutinesTest"), false),
                Pair(kotlin("test"), false)
            )
            dependencyNotationPairs.forEach { dependencyNotationPair ->
                val dependencyNotation = dependencyNotationPair.first
                val alsoImplementation = dependencyNotationPair.second
                setDependency(dependencyNotation, alsoImplementation)
            }

        }
    }

    fun DependencyHandlerScope.setDependency(dependencyNotation: Any, alsoImplementation: Boolean)

    fun PluginManager.applyPlugins() {}

}