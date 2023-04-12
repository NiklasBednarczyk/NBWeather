plugins {
    id("de.niklasbednarczyk.nbweather.library.test")
    id("de.niklasbednarczyk.nbweather.layer.ui")
}
dependencies {
    implementation(project(":core-ui"))
    implementation(project(":test-common"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.test.ui"
}
