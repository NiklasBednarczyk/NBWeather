plugins {
    `kotlin-dsl`
}

group = "de.niklasbednarczyk.nbweather.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.protobuf.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "de.niklasbednarczyk.nbweather.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "de.niklasbednarczyk.nbweather.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("dependencyHilt") {
            id = "de.niklasbednarczyk.nbweather.dependency.hilt"
            implementationClass = "DependencyHiltConventionPlugin"
        }
        register("layerDataDisk") {
            id = "de.niklasbednarczyk.nbweather.layer.data.disk"
            implementationClass = "LayerDataDiskConventionPlugin"
        }
        register("layerDataLocalRemote") {
            id = "de.niklasbednarczyk.nbweather.layer.data.localremote"
            implementationClass = "LayerDataLocalRemoteConventionPlugin"
        }
        register("layerDataLocalRemoteLocal") {
            id = "de.niklasbednarczyk.nbweather.layer.data.localremote.local"
            implementationClass = "LayerDataLocalRemoteLocalConventionPlugin"
        }
        register("layerDataLocalRemoteRemote") {
            id = "de.niklasbednarczyk.nbweather.layer.data.localremote.remote"
            implementationClass = "LayerDataLocalRemoteRemoteConventionPlugin"
        }
        register("layerUi") {
            id = "de.niklasbednarczyk.nbweather.layer.ui"
            implementationClass = "LayerUiConventionPlugin"
        }
    }
}
