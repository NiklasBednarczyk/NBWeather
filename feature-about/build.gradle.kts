plugins {
    id("de.niklasbednarczyk.nbweather.android.library")
    id("de.niklasbednarczyk.nbweather.dependency.hilt")
    id("de.niklasbednarczyk.nbweather.layer.ui")
}

dependencies {
    implementation(project(":core-ui"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.feature.about"
}