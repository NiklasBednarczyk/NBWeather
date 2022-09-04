import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies

class LayerDataLocalRemoteRemoteConventionPlugin : OwmConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        with(pluginManager) {
            apply("kotlin-kapt")
        }

        dependencies {
            implementation(libs.getLibrary("com.squareup.moshi.moshi"))
            implementation(libs.getLibrary("com.squareup.moshi.moshiKotlin"))
            kapt(libs.getLibrary("com.squareup.moshi.moshiKotlinCodegen"))

            implementation(libs.getLibrary("com.squareup.okhttp3.okhttp"))

            implementation(libs.getLibrary("com.squareup.retrofit2.converterMoshi"))
            implementation(libs.getLibrary("com.squareup.retrofit2.retrofit"))
        }
    }

}