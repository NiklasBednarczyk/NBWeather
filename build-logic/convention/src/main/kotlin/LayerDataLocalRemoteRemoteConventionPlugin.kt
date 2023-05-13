import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class LayerDataLocalRemoteRemoteConventionPlugin : NBConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        plugins {
            apply("com.google.devtools.ksp")
        }

        dependencies {
            implementation(project(":core-common"))

            androidTestImplementation(project(":test-common"))

            implementation(libs.getLibrary("com.squareup.moshi.moshi"))
            implementation(libs.getLibrary("com.squareup.moshi.moshiKotlin"))
            ksp(libs.getLibrary("com.squareup.moshi.moshiKotlinCodegen"))

            implementation(libs.getLibrary("com.squareup.okhttp3.okhttp"))

            implementation(libs.getLibrary("com.squareup.retrofit2.converterMoshi"))
            implementation(libs.getLibrary("com.squareup.retrofit2.retrofit"))
        }
    }

}