import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class LayerUiConventionPlugin : OwmConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        getCommonExtensionOrNull()?.apply {
            buildFeatures {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = libs.getVersion("androidxComposeCompiler")
            }
        }

        dependencies {
            implementation(project(":core-common"))
            implementation(project(":core-data-disk"))
            implementation(project(":core-data-localremote"))

            implementation(project(":library-materialcolorutilities"))

            implementation(libs.getLibrary("androidx.activity.activityCompose"))
            implementation(libs.getLibrary("androidx.compose.material3.material3"))
            implementation(libs.getLibrary("androidx.compose.ui.ui"))
            implementation(libs.getLibrary("androidx.core.coreKtx"))
            implementation(libs.getLibrary("androidx.hilt.hiltNavigationCompose"))
            implementation(libs.getLibrary("androidx.lifecycle.lifecycleRuntimeKtx"))
            implementation(libs.getLibrary("androidx.navigation.navigationCompose"))
            implementation(libs.getLibrary("com.google.accompanist.accompanistNavigationAnimation"))
            implementation(libs.getLibrary("com.google.accompanist.accompanistPermissions"))
            implementation(libs.getLibrary("com.google.accompanist.accompanistPlaceholderMaterial"))
            implementation(libs.getLibrary("com.google.accompanist.accompanistSystemuicontroller"))
            implementation(libs.getLibrary("com.google.android.material.material"))
        }
    }

}