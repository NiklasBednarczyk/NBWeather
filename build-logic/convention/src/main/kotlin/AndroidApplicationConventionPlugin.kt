import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : OwmConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        with(pluginManager) {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
        }

        extensions.configure<BaseAppModuleExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = libs.getVersionInt("targetSdk")
        }
    }

}