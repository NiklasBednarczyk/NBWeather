import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

internal interface NBTestConventionPlugin : NBConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        plugins {
            apply("de.niklasbednarczyk.nbweather.android.library")
        }
        dependencies {
            val dependencyNotations = listOf(
                libs.getLibrary("androidx.test.core"),
                libs.getLibrary("androidx.test.runner"),
                libs.getLibrary("com.google.dagger.hiltAndroidTesting"),
                libs.getLibrary("org.jetbrains.kotlinx.kotlinxCoroutinesTest"),
                kotlin("test")
            )
            dependencyNotations.forEach { dependencyNotation ->
                setDependency(dependencyNotation)
            }

        }
    }

    fun DependencyHandlerScope.setDependency(dependencyNotation: Any)

}