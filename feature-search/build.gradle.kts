plugins {
    id("de.niklasbednarczyk.openweathermap.android.library")
    id("de.niklasbednarczyk.openweathermap.dependency.hilt")
    id("de.niklasbednarczyk.openweathermap.layer.ui")
}

dependencies {
    implementation(project(":core-ui"))

    implementation(project(":data-geocoding"))
    implementation(project(":data-settings"))
}