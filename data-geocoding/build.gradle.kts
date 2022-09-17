plugins {
    id("de.niklasbednarczyk.openweathermap.android.library")
    id("de.niklasbednarczyk.openweathermap.dependency.hilt")
    id("de.niklasbednarczyk.openweathermap.layer.data.localremote")
}

dependencies {
    implementation(project(":core-data-localremote"))

    implementation(project(":data-geocoding-local"))
    implementation(project(":data-geocoding-remote"))
}