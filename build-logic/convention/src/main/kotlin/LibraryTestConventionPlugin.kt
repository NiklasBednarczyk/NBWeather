import org.gradle.kotlin.dsl.DependencyHandlerScope

class LibraryTestConventionPlugin : NBTestConventionPlugin {

    override fun DependencyHandlerScope.setDependency(dependencyNotation: Any) {
        implementation(dependencyNotation)
    }

}