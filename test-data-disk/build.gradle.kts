plugins {
    id("de.niklasbednarczyk.nbweather.library.test")
    id("de.niklasbednarczyk.nbweather.layer.data.disk")
}
dependencies {
    implementation(project(":core-data-disk"))
    implementation(project(":test-common"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.test.data.disk"
}
