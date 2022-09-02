plugins {
    id("de.niklasbednarczyk.openweathermap.android.library")
    id("de.niklasbednarczyk.openweathermap.dependency.hilt")
}

dependencies {
    implementation(project(":core-data"))

    implementation(project(":data-onecall-disk"))
    implementation(project(":data-onecall-local"))
    implementation(project(":data-onecall-remote"))
}