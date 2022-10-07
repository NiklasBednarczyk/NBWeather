plugins {
    id("de.niklasbednarczyk.openweathermap.android.library")
    id("de.niklasbednarczyk.openweathermap.dependency.hilt")
    id("de.niklasbednarczyk.openweathermap.layer.data.localremote.local")
}

dependencies {
    implementation(project(":core-data-localremote-local"))
}
android {
    namespace = "de.niklasbednarczyk.openweathermap.data.onecall.local"
}
