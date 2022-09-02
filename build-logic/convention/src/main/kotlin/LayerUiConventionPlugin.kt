import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies

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
            implementation(libs.getDependency("androidx.activity.activityCompose"))
            implementation(libs.getDependency("androidx.compose.material3.material3"))
            implementation(libs.getDependency("androidx.compose.ui.ui"))
            implementation(libs.getDependency("androidx.core.coreKtx"))
            implementation(libs.getDependency("androidx.hilt.hiltNavigationCompose"))
            implementation(libs.getDependency("androidx.lifecycle.lifecycleRuntimeKtx"))
            implementation(libs.getDependency("androidx.navigation.navigationCompose"))
            implementation(libs.getDependency("com.google.android.material.material"))
        }
    }

}