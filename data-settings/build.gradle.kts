plugins {
    id("de.niklasbednarczyk.openweathermap.android.library")
    id("de.niklasbednarczyk.openweathermap.dependency.hilt")
    id("de.niklasbednarczyk.openweathermap.layer.data.disk")
}

dependencies {
    implementation(project(":core-data-disk"))
}
android {
    namespace = "de.niklasbednarczyk.openweathermap.data.settings"
}
