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
                libs.getLibrary("androidx.compose.ui.uiTestJunit4").toPair(),
                libs.getLibrary("androidx.compose.ui.uiTestManifest").toPair(true),
                libs.getLibrary("androidx.test.core").toPair(true),
                libs.getLibrary("androidx.test.espresso.espressoCore").toPair(),
                libs.getLibrary("androidx.test.runner").toPair(),
                libs.getLibrary("com.google.dagger.hiltAndroidTesting").toPair(true),
                libs.getLibrary("org.jetbrains.kotlinx.kotlinxCoroutinesTest").toPair(),
                kotlin("test").toPair()
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

    private fun Any.toPair(alsoImplementation: Boolean = false) = Pair(this, alsoImplementation)

}