import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies

class DependencyHiltConventionPlugin : NBConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        with(pluginManager) {
            apply("dagger.hilt.android.plugin")
            apply("kotlin-kapt")
        }

        dependencies {
            implementation(libs.getLibrary("com.google.dagger.hiltAndroid"))
            kapt(libs.getLibrary("com.google.dagger.hiltCompiler"))
        }
    }

}