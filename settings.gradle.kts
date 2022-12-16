pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NBWeather"

include(":app")

include(":core-common")
include(":core-data-disk")
include(":core-data-localremote")
include(":core-data-localremote-local")
include(":core-data-localremote-remote")
include(":core-ui")

include(":data-geocoding")
include(":data-geocoding-local")
include(":data-geocoding-remote")
include(":data-onecall")
include(":data-onecall-local")
include(":data-onecall-remote")
include(":data-settings")

include(":feature-location")
include(":feature-search")
include(":feature-settings")

//TODO (Material) Remove when available as gradle dependency (blocked by Material)
include(":library-materialcolorutilities")
