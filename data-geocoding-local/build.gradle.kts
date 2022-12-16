plugins {
    id("de.niklasbednarczyk.nbweather.android.library")
    id("de.niklasbednarczyk.nbweather.dependency.hilt")
    id("de.niklasbednarczyk.nbweather.layer.data.localremote.local")
}

dependencies {
    implementation(project(":core-data-localremote-local"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.data.geocoding.local"
}
