plugins {
    id("de.niklasbednarczyk.nbweather.android.library")
    id("de.niklasbednarczyk.nbweather.dependency.hilt")
    id("de.niklasbednarczyk.nbweather.layer.data.localremote.remote")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}
android {
    namespace = "de.niklasbednarczyk.nbweather.core.data.localremote.remote"

    buildFeatures {
        buildConfig = true
    }
}
