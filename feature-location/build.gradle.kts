plugins {
    id("de.niklasbednarczyk.nbweather.library.ui")
}
dependencies {
    implementation(project(":data-geocoding"))
    implementation(project(":data-onecall"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.feature.location"
}
