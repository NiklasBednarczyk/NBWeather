import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies

class LayerDataLocalRemoteLocalConventionPlugin : NBConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        plugins {
            apply("kotlin-kapt")
        }

        dependencies {
            implementation(project(":core-common"))

            annotationProcessor(libs.getLibrary("androidx.room.roomCompiler"))
            kapt(libs.getLibrary("androidx.room.roomCompiler"))
            implementation(libs.getLibrary("androidx.room.roomKtx"))
            implementation(libs.getLibrary("androidx.room.roomRuntime"))
        }
    }

}