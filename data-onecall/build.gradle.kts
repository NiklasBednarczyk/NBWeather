plugins {
    id("de.niklasbednarczyk.nbweather.android.library")
    id("de.niklasbednarczyk.nbweather.dependency.hilt")
    id("de.niklasbednarczyk.nbweather.layer.data.localremote")
}

dependencies {
    implementation(project(":core-data-localremote"))

    implementation(project(":data-onecall-local"))
    implementation(project(":data-onecall-remote"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.data.onecall"
}
