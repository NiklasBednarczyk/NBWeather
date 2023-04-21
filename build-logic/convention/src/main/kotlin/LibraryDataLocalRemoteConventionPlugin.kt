import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class LibraryDataLocalRemoteConventionPlugin : NBConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        plugins {
            apply("de.niklasbednarczyk.nbweather.android.library")
            apply("de.niklasbednarczyk.nbweather.dependency.hilt")
            apply("de.niklasbednarczyk.nbweather.dependency.test")
            apply("de.niklasbednarczyk.nbweather.layer.data.localremote")
        }
        dependencies {
            implementation(project(":core-data-localremote"))

            androidTestImplementation(project(":test-common"))
            androidTestImplementation(project(":test-data-localremote"))
        }
    }

}