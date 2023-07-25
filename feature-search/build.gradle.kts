plugins {
    id("de.niklasbednarczyk.nbweather.library.ui")
}
dependencies {
    implementation(project(":data-geocoding"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.feature.search"
}
