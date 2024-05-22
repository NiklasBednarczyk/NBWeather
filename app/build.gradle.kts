import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("de.niklasbednarczyk.nbweather.android.application")
    id("de.niklasbednarczyk.nbweather.dependency.hilt")
    id("de.niklasbednarczyk.nbweather.dependency.test")
    id("de.niklasbednarczyk.nbweather.layer.ui")

    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")
}

android {
    signingConfigs {
        create("release") {
            val localProperties = gradleLocalProperties(rootDir, providers)

            val storeFilePath = localProperties.getProperty("signingconfig.release.storefilepath")
            storeFile = if (storeFilePath != null) file(storeFilePath) else null
            keyAlias = localProperties.getProperty("signingconfig.release.keyalias")
            storePassword = localProperties.getProperty("signingconfig.release.storepassword")
            keyPassword = localProperties.getProperty("signingconfig.release.keypassword")
        }
    }
    defaultConfig {
        applicationId = "de.niklasbednarczyk.nbweather"
        versionCode = 8
        versionName = "2.1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = NBBuildType.DEBUG.applicationIdSuffix
        }
        getByName("release") {
            applicationIdSuffix = NBBuildType.RELEASE.applicationIdSuffix
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
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
    implementation(project(":feature-forecast"))
    implementation(project(":feature-search"))
    implementation(project(":feature-settings"))

    androidTestImplementation(project(":test-ui"))
}