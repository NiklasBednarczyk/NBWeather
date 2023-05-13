plugins {
    `kotlin-dsl`
}

group = "de.niklasbednarczyk.nbweather.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
        register("dependencyTest") {
            id = "de.niklasbednarczyk.nbweather.dependency.test"
            implementationClass = "DependencyTestConventionPlugin"
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
        register("libraryDataDisk") {
            id = "de.niklasbednarczyk.nbweather.library.data.disk"
            implementationClass = "LibraryDataDiskConventionPlugin"
        }
        register("libraryDataLocalRemote") {
            id = "de.niklasbednarczyk.nbweather.library.data.localremote"
            implementationClass = "LibraryDataLocalRemoteConventionPlugin"
        }
        register("libraryDataLocalRemoteLocal") {
            id = "de.niklasbednarczyk.nbweather.library.data.localremote.local"
            implementationClass = "LibraryDataLocalRemoteLocalConventionPlugin"
        }
        register("libraryDataLocalRemoteRemote") {
            id = "de.niklasbednarczyk.nbweather.library.data.localremote.remote"
            implementationClass = "LibraryDataLocalRemoteRemoteConventionPlugin"
        }
        register("libraryTest") {
            id = "de.niklasbednarczyk.nbweather.library.test"
            implementationClass = "LibraryTestConventionPlugin"
        }
        register("libraryUi") {
            id = "de.niklasbednarczyk.nbweather.library.ui"
            implementationClass = "LibraryUiConventionPlugin"
        }
    }
}
