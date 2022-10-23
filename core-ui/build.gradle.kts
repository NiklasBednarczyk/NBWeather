plugins {
    id("de.niklasbednarczyk.openweathermap.android.library")
    id("de.niklasbednarczyk.openweathermap.layer.ui")
}
dependencies {
    implementation(project(":data-settings"))
}
android {
    namespace = "de.niklasbednarczyk.openweathermap.core.ui"
}
