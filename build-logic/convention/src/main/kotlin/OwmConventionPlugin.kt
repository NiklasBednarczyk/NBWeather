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
import org.gradle.kotlin.dsl.dependencies
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

    fun DependencyHandlerScope.coreLibraryDesugaring(dependencyNotation: Any) {
        add("coreLibraryDesugaring", dependencyNotation)
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
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
                isCoreLibraryDesugaringEnabled = true
            }

            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + listOf(
                    // Enables animation navigation
                    "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                    // Enables animateItemPlacement
                    "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                    // Enables material 3
                    "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                    // Enables material 3 window size class
                    "-opt-in=androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi",
                    // Enables pager
                    "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
                    // Enables permissions
                    "-opt-in=com.google.accompanist.permissions.ExperimentalPermissionsApi",
                    // Enables flatMapLatest
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    // Enables debounce
                    "-opt-in=kotlinx.coroutines.FlowPreview"
                )

                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }

        dependencies {
            coreLibraryDesugaring(libs.getLibrary("com.android.tools.desugarJdkLibs"))
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

    fun VersionCatalog.getLibrary(name: String): Any {
        return findLibrary(name).get()
    }

    fun VersionCatalog.getLibraryString(name: String): String {
        val library = findLibrary(name).get().get()
        val moduleGroup = library.module.group
        val moduleName = library.module.name
        val version = library.versionConstraint.displayName
        return "$moduleGroup:$moduleName:$version"
    }

    fun VersionCatalog.getVersion(name: String): String {
        return findVersion(name).get().toString()
    }

    fun VersionCatalog.getVersionInt(name: String): Int? {
        return getVersion(name).toIntOrNull()
    }

}