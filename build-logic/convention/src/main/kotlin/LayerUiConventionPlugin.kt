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

            implementation(project(":library-materialcolorutilities"))

            implementation(libs.getLibrary("androidx.activity.activityCompose"))
            implementation(libs.getLibrary("androidx.activity.activityKtx"))
            implementation(libs.getLibrary("androidx.compose.material3.material3"))
            implementation(libs.getLibrary("androidx.compose.material3.material3WindowSizeClass"))
            implementation(libs.getLibrary("androidx.compose.ui.ui"))
            implementation(libs.getLibrary("androidx.compose.ui.uiViewbinding"))
            implementation(libs.getLibrary("androidx.core.coreKtx"))
            implementation(libs.getLibrary("androidx.hilt.hiltNavigationFragment"))
            implementation(libs.getLibrary("androidx.lifecycle.lifecycleRuntimeKtx"))
            implementation(libs.getLibrary("androidx.navigation.navigationFragmentKtx"))
            implementation(libs.getLibrary("androidx.navigation.navigationUiKtx"))
            implementation(libs.getLibrary("com.google.accompanist.accompanistFlowlayout"))
            implementation(libs.getLibrary("com.google.accompanist.accompanistPager"))
            implementation(libs.getLibrary("com.google.accompanist.accompanistSwiperefresh"))
            implementation(libs.getLibrary("com.google.accompanist.accompanistSystemuicontroller"))
            implementation(libs.getLibrary("com.google.android.gms.playServicesLocation"))
            implementation(libs.getLibrary("com.google.android.material.material"))
        }
    }

}