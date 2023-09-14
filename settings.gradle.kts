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

include(":data-airpollution")
include(":data-airpollution-local")
include(":data-airpollution-remote")
include(":data-geocoding")
include(":data-geocoding-local")
include(":data-geocoding-remote")
include(":data-onecall")
include(":data-onecall-local")
include(":data-onecall-remote")
include(":data-settings")

include(":feature-about")
include(":feature-forecast")
include(":feature-search")
include(":feature-settings")

include(":test-common")
include(":test-data-disk")
include(":test-data-localremote")
include(":test-data-localremote-local")
include(":test-data-localremote-remote")
include(":test-ui")
