import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies

class LayerDataLocalRemoteConventionPlugin : OwmConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        dependencies {
            implementation(project(":core-common"))
            implementation(project(":core-data-localremote-local"))
            implementation(project(":core-data-localremote-remote"))

            implementation(libs.getLibrary("org.jetbrains.kotlinx.kotlinxCoroutinesCore"))
        }
    }

}