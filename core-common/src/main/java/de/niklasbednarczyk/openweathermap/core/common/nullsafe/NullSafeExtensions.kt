package de.niklasbednarczyk.openweathermap.core.common.nullsafe

fun <T1, R> owmNullSafe(
    value1: T1?,
    transform: (value: T1) -> R?
): R? {
    return if (value1 != null) {
        transform(value1)
    } else {
        null
    }
}

fun <T1, T2, R> owmNullSafe(
    value1: T1?,
    value2: T2?,
    transform: (value1: T1, value2: T2) -> R?
): R? {
    return if (value1 != null && value2 != null) {
        transform(value1, value2)
    } else {
        null
    }
}