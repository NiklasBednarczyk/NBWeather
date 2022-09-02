plugins {
    id("de.niklasbednarczyk.openweathermap.android.library")
    id("de.niklasbednarczyk.openweathermap.dependency.hilt")
}

dependencies {
    implementation(project(":core-data-disk"))
}