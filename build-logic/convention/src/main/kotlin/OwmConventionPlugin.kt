import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal interface OwmConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = getLibs()
            apply(libs)
        }
    }

    fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
        (this as ExtensionAware).extensions.configure("kotlinOptions", block)
    }

    fun DependencyHandlerScope.annotationProcessor(dependencyNotation: Any) {
        add("annotationProcessor", dependencyNotation)
    }

    fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
        add("implementation", dependencyNotation)
    }

    fun DependencyHandlerScope.kapt(dependencyNotation: Any) {
        add("kapt", dependencyNotation)
    }

    fun Project.apply(libs: VersionCatalog)

    fun Project.configureKotlinAndroid(
        commonExtension: CommonExtension<*, *, *, *>,
    ) {
        val libs = getLibs()

        commonExtension.apply {
            compileSdk = libs.getVersionInt("compileSdk")

            defaultConfig {
                minSdk = libs.getVersionInt("minSdk")
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }

    }

    fun Project.getCommonExtensionOrNull(): CommonExtension<*, *, *, *>? {
        return try {
            extensions.getByType<LibraryExtension>()
        } catch (e: Exception) {
            try {
                extensions.getByType<BaseAppModuleExtension>()
            } catch (e: Exception) {
                null
            }
        }
    }

    fun Project.getLibs(): VersionCatalog {
        return extensions.getByType<VersionCatalogsExtension>().named("libs")
    }

    fun VersionCatalog.getDependency(name: String): Any {
        return findDependency(name).get()
    }

    fun VersionCatalog.getVersion(name: String): String {
        return findVersion(name).get().toString()
    }

    fun VersionCatalog.getVersionInt(name: String): Int? {
        return getVersion(name).toIntOrNull()
    }

}