plugins {
    id("de.niklasbednarczyk.nbweather.library.data.localremote")
}
dependencies {
    implementation(project(":data-geocoding-local"))
    implementation(project(":data-geocoding-remote"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.data.geocoding"
}
