plugins {
    `kotlin-dsl`
}

group = "de.niklasbednarczyk.openweathermap.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "de.niklasbednarczyk.openweathermap.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "de.niklasbednarczyk.openweathermap.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("dependencyHilt") {
            id = "de.niklasbednarczyk.openweathermap.dependency.hilt"
            implementationClass = "DependencyHiltConventionPlugin"
        }
        register("layerDataLocalRemoteLocal") {
            id = "de.niklasbednarczyk.openweathermap.layer.data.localremote.local"
            implementationClass = "LayerDataLocalRemoteLocalConventionPlugin"
        }
        register("layerDataLocalRemoteRemote") {
            id = "de.niklasbednarczyk.openweathermap.layer.data.localremote.remote"
            implementationClass = "LayerDataLocalRemoteRemoteConventionPlugin"
        }
        register("layerUi") {
            id = "de.niklasbednarczyk.openweathermap.layer.ui"
            implementationClass = "LayerUiConventionPlugin"
        }
    }
}
