import org.gradle.kotlin.dsl.DependencyHandlerScope

class DependencyTestConventionPlugin : NBTestConventionPlugin {

    override fun DependencyHandlerScope.setDependency(
        dependencyNotation: Any,
        alsoImplementation: Boolean
    ) {
        androidTestImplementation(dependencyNotation)
        if (alsoImplementation) {
            debugImplementation(dependencyNotation)
        }
    }

}