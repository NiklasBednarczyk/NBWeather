plugins {
    id("de.niklasbednarczyk.openweathermap.android.library")
    id("de.niklasbednarczyk.openweathermap.dependency.hilt")
    id("de.niklasbednarczyk.openweathermap.layer.data.localremote.remote")
}

dependencies {
    implementation(project(":core-data-localremote-remote"))
}
android {
    namespace = "de.niklasbednarczyk.openweathermap.data.geocoding.remote"
}