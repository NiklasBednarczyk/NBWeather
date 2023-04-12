plugins {
    id("de.niklasbednarczyk.nbweather.library.test")
    id("de.niklasbednarczyk.nbweather.layer.data.localremote")
}
dependencies {
    implementation(project(":core-data-localremote"))
    implementation(project(":test-common"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.test.data.localremote"
}
