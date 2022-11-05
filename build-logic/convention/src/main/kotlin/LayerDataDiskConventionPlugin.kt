import com.google.protobuf.gradle.*
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies

class LayerDataDiskConventionPlugin : OwmConventionPlugin {

    override fun Project.apply(libs: VersionCatalog) {
        with(pluginManager) {
            apply("com.google.protobuf")
        }

        protobuf {
            protoc {
                artifact = libs.getLibraryString("com.google.protobuf.protoc")
            }

            generateProtoTasks {
                all().forEach { task ->
                    task.builtins {
                        id("java") {
                            option("lite")
                        }
                    }
                }
            }
        }

        dependencies {
            implementation(project(":core-common"))

            implementation(libs.getLibrary("androidx.datastore.datastore"))
            implementation(libs.getLibrary("com.google.protobuf.protobufJavalite"))
        }

    }

}