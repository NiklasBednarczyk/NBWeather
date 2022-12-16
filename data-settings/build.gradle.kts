plugins {
    id("de.niklasbednarczyk.nbweather.android.library")
    id("de.niklasbednarczyk.nbweather.dependency.hilt")
    id("de.niklasbednarczyk.nbweather.layer.data.disk")
}

dependencies {
    implementation(project(":core-data-disk"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.data.settings"
}
