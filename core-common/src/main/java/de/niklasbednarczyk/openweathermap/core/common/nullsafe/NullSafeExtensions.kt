package de.niklasbednarczyk.openweathermap.core.common.nullsafe

fun <T, R> owmNullSafe(
    value: T?,
    transform: (value: T) -> R?
): R? {
    return if (value != null) {
        transform(value)
    } else {
        null
    }
}