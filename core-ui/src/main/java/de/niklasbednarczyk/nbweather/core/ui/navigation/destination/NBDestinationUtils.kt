package de.niklasbednarczyk.nbweather.core.ui.navigation.destination

import java.util.regex.Pattern
import kotlin.reflect.KClass

private const val HYPHEN_REGEX = "(?!^)(?=[A-Z][a-z])"
private const val HYPHEN_STRING = "-"

private val hyphenPattern = Pattern.compile(HYPHEN_REGEX)

val KClass<*>.destinationString: String
    get() {
        val simpleName = java.simpleName
        val matcher = hyphenPattern.matcher(simpleName)
        val result = matcher.replaceAll(HYPHEN_STRING)
        return result.lowercase()
    }