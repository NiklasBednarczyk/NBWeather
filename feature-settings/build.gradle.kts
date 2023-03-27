plugins {
    id("de.niklasbednarczyk.nbweather.library.ui")
}
dependencies {
    implementation(project(":data-settings"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.feature.settings"
}
