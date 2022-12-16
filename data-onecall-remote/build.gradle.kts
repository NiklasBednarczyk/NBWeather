plugins {
    id("de.niklasbednarczyk.nbweather.android.library")
    id("de.niklasbednarczyk.nbweather.dependency.hilt")
    id("de.niklasbednarczyk.nbweather.layer.data.localremote.remote")
}

dependencies {
    implementation(project(":core-data-localremote-remote"))
}
android {
    namespace = "de.niklasbednarczyk.nbweather.data.onecall.remote"
}
