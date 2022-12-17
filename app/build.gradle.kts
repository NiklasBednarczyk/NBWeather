plugins {
    id("de.niklasbednarczyk.nbweather.android.application")
    id("de.niklasbednarczyk.nbweather.dependency.hilt")
    id("de.niklasbednarczyk.nbweather.layer.ui")
}

android {
    defaultConfig {
        applicationId = "de.niklasbednarczyk.nbweather"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    namespace = "de.niklasbednarczyk.nbweather"
}

dependencies {
    implementation(project(":core-ui"))

    implementation(project(":data-geocoding"))
    implementation(project(":data-settings"))

    implementation(project(":feature-about"))
    implementation(project(":feature-location"))
    implementation(project(":feature-search"))
    implementation(project(":feature-settings"))
}