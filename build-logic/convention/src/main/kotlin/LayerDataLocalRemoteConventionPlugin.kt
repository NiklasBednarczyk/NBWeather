import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class LayerDataLocalRemoteConventionPlugin : NBConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        dependencies {
            implementation(project(":core-common"))
            implementation(project(":core-data-localremote-local"))
            implementation(project(":core-data-localremote-remote"))

            androidTestImplementation(project(":test-common"))

            implementation(libs.getLibrary("org.jetbrains.kotlinx.kotlinxCoroutinesCore"))
        }
    }

}