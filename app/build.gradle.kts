plugins {
    id("de.niklasbednarczyk.openweathermap.android.application")
    id("de.niklasbednarczyk.openweathermap.dependency.hilt")
    id("de.niklasbednarczyk.openweathermap.layer.ui")
}

android {
    defaultConfig {
        applicationId = "de.niklasbednarczyk.openweathermap"
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
}

dependencies {
    implementation(project(":feature-location"))
}