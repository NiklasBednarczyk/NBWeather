import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.DependencyHandlerScope

class LibraryTestConventionPlugin : NBTestConventionPlugin {

    override fun DependencyHandlerScope.setDependency(
        dependencyNotation: Any,
        alsoImplementation: Boolean
    ) {
        debugImplementation(dependencyNotation)
    }

    override fun PluginManager.applyPlugins() {
        apply("de.niklasbednarczyk.nbweather.android.library")
    }

}