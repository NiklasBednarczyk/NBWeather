import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies

class LayerDataLocalRemoteLocalConventionPlugin : OwmConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        with(pluginManager) {
            apply("kotlin-kapt")
        }

        dependencies {
            annotationProcessor(libs.getDependency("androidx.room.roomCompiler"))
            kapt(libs.getDependency("androidx.room.roomCompiler"))
            implementation(libs.getDependency("androidx.room.roomKtx"))
            implementation(libs.getDependency("androidx.room.roomRuntime"))
        }
    }

}