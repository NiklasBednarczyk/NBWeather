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

rootProject.name = "OpenWeatherMap"

include(":app")

include(":core-common")
include(":core-data")
include(":core-data-disk")
include(":core-data-local")
include(":core-data-remote")
include(":core-ui")

include(":data-airpollution")
include(":data-airpollution-local")
include(":data-airpollution-remote")
include(":data-onecall")
include(":data-onecall-disk")
include(":data-onecall-local")
include(":data-onecall-remote")

include(":feature-location")
