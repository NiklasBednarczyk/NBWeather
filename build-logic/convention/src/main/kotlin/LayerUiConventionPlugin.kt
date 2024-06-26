import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class LayerUiConventionPlugin : NBConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        getCommonExtensionOrNull()?.apply {
            buildFeatures {
                compose = true
                viewBinding = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = libs.getVersion("androidxComposeCompiler")
            }
        }

        dependencies {
            implementation(project(":core-common"))
            implementation(project(":core-data-disk"))
            implementation(project(":core-data-localremote"))

            androidTestImplementation(project(":test-common"))

            implementation(libs.getLibrary("androidx.activity.activityCompose"))
            implementation(libs.getLibrary("androidx.compose.material3.material3"))
            implementation(libs.getLibrary("androidx.compose.material3.material3WindowSizeClass"))
            implementation(libs.getLibrary("androidx.compose.ui.ui"))
            implementation(libs.getLibrary("androidx.compose.ui.uiUtil"))
            implementation(libs.getLibrary("androidx.compose.ui.uiViewbinding"))
            implementation(libs.getLibrary("androidx.core.coreKtx"))
            implementation(libs.getLibrary("androidx.hilt.hiltNavigationCompose"))
            implementation(libs.getLibrary("androidx.lifecycle.lifecycleRuntimeCompose"))
            implementation(libs.getLibrary("androidx.navigation.navigationCompose"))
            implementation(libs.getLibrary("com.google.android.gms.playServicesLocation"))
            implementation(libs.getLibrary("com.google.android.material.material"))
        }
    }

}