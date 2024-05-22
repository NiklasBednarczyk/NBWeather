import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.reporting) apply true
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.parcelize) apply false
    alias(libs.plugins.protobuf) apply false
    alias(libs.plugins.secrets) apply false
    alias(libs.plugins.versions) apply true
}

// com.github.ben-manes.versions
tasks.withType(DependencyUpdatesTask::class) {
    revision = "release"
    outputFormatter = "html"

    rejectVersionIf {
        !isStableVersion(candidate.version) && isStableVersion(currentVersion)
    }
}

fun isStableVersion(version: String): Boolean {
    val stableKeyword =
        listOf("RELEASE", "FINAL", "GA").any { keyword -> version.uppercase().contains(keyword) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    return stableKeyword || regex.matches(version)
}