import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("de.niklasbednarczyk.nbweather.android.application")
    id("de.niklasbednarczyk.nbweather.dependency.hilt")
    id("de.niklasbednarczyk.nbweather.layer.ui")

    id("com.google.gms.google-services")
}

android {
    signingConfigs {
        create("release") {
            storeFile = file(gradleLocalProperties(rootDir).getProperty("signingconfig.release.storefilepath"))
            keyAlias = gradleLocalProperties(rootDir).getProperty("signingconfig.release.keyalias")
            storePassword = gradleLocalProperties(rootDir).getProperty("signingconfig.release.storepassword")
            keyPassword = gradleLocalProperties(rootDir).getProperty("signingconfig.release.keypassword")
        }
    }
    defaultConfig {
        applicationId = "de.niklasbednarczyk.nbweather"
        versionCode = 2
        versionName = "1.0.1"

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
            signingConfig = signingConfigs.getByName("release")
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