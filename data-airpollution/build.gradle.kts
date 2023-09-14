plugins {
    id("de.niklasbednarczyk.nbweather.library.data.localremote")
}
dependencies {
    implementation(project(":data-airpollution-local"))
    implementation(project(":data-airpollution-remote"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.data.airpollution"
}
