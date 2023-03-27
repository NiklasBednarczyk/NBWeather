plugins {
    id("de.niklasbednarczyk.nbweather.library.data.localremote")
}
dependencies {
    implementation(project(":data-onecall-local"))
    implementation(project(":data-onecall-remote"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.data.onecall"
}
