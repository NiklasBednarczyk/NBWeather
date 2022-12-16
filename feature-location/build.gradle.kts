plugins {
    id("de.niklasbednarczyk.nbweather.android.library")
    id("de.niklasbednarczyk.nbweather.dependency.hilt")
    id("de.niklasbednarczyk.nbweather.layer.ui")
}

dependencies {
    implementation(project(":core-ui"))

    implementation(project(":data-geocoding"))
    implementation(project(":data-onecall"))
    implementation(project(":data-settings"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.feature.location"
}
