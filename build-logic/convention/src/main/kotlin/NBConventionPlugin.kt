import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.google.protobuf.gradle.ProtobufExtension
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal interface NBConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = getLibs()
            apply(libs)
        }
    }

    fun CommonExtension<*, *, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
        (this as ExtensionAware).extensions.configure("kotlinOptions", block)
    }

    fun DependencyHandlerScope.androidTestImplementation(dependencyNotation: Any) {
        add("androidTestImplementation", dependencyNotation)
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

    fun DependencyHandlerScope.kaptAndroidTest(dependencyNotation: Any) {
        add("kaptAndroidTest", dependencyNotation)
    }

    fun DependencyHandlerScope.ksp(dependencyNotation: Any) {
        add("ksp", dependencyNotation)
    }

    fun PluginManager.configurePlugins() {
        apply("org.jetbrains.kotlin.android")
        apply("org.jetbrains.kotlin.plugin.parcelize")
    }

    fun Project.plugins(block: PluginManager.() -> Unit) {
        with(pluginManager, block)
    }

    val Project.protobuf: ProtobufExtension
        get() =
            (this as ExtensionAware).extensions.getByName("protobuf") as ProtobufExtension

    fun Project.protobuf(configure: Action<ProtobufExtension>): Unit =
        (this as ExtensionAware).extensions.configure("protobuf", configure)


    fun Project.apply(libs: VersionCatalog)

    fun Project.configureKotlinAndroid(
        commonExtension: CommonExtension<*, *, *, *, *, *>,
    ) {
        val libs = getLibs()

        commonExtension.apply {
            compileSdk = libs.getVersionInt("compileSdk")

            defaultConfig {
                minSdk = libs.getVersionInt("minSdk")

                resourceConfigurations.addAll(listOf("de", "en"))

                testInstrumentationRunner =
                    "de.niklasbednarczyk.nbweather.test.common.runner.NBTestRunner"
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
                isCoreLibraryDesugaringEnabled = true
            }

            flavorDimensions += NBProductFlavorDimension.getFlavorDimensionStrings()
            productFlavors {
                NBProductFlavor.values().forEach { flavor ->
                    create(flavor.toString()) {
                        dimension = flavor.dimension.toString()
                        if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                            applicationIdSuffix = flavor.applicationIdSuffix
                        }
                    }
                }
            }

            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }

        dependencies {
            coreLibraryDesugaring(libs.getLibrary("com.android.tools.desugarJdkLibs"))

            implementation(libs.getLibrary("com.google.firebase.firebaseCrashlyticsKtx"))
            implementation(libs.getLibrary("com.jakewharton.timber.timber"))
        }

    }

    fun Project.getCommonExtensionOrNull(): CommonExtension<*, *, *, *, *, *>? {
        return try {
            extensions.getByType<LibraryExtension>()
        } catch (throwable: Throwable) {
            try {
                extensions.getByType<BaseAppModuleExtension>()
            } catch (throwable: Throwable) {
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