plugins {
    id("de.niklasbednarczyk.openweathermap.android.library")
    id("de.niklasbednarczyk.openweathermap.dependency.hilt")
    id("de.niklasbednarczyk.openweathermap.layer.data.localremote")
}

dependencies {
    implementation(project(":core-data-localremote"))

    implementation(project(":data-airpollution-local"))
    implementation(project(":data-airpollution-remote"))
}