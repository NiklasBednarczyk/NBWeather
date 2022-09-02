import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies

class LayerDataRemoteConventionPlugin : OwmConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        with(pluginManager) {
            apply("kotlin-kapt")
        }

        dependencies {
            implementation(libs.getDependency("com.squareup.moshi.moshi"))
            implementation(libs.getDependency("com.squareup.moshi.moshiKotlin"))
            kapt(libs.getDependency("com.squareup.moshi.moshiKotlinCodegen"))

            implementation(libs.getDependency("com.squareup.okhttp3.okhttp"))

            implementation(libs.getDependency("com.squareup.retrofit2.converterMoshi"))
            implementation(libs.getDependency("com.squareup.retrofit2.retrofit"))
        }
    }

}