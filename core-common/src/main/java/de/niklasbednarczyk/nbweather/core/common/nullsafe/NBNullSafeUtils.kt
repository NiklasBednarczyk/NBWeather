package de.niklasbednarczyk.nbweather.core.common.nullsafe

inline fun <T1, R> nbNullSafe(
    value1: T1?,
    transform: (value1: T1) -> R?
): R? {
    return if (value1 != null) {
        transform(value1)
    } else {
        null
    }
}

inline fun <T1, R> nbNullSafeList(
    value1: List<T1>?,
    transform: (value1: List<T1>) -> R?
): R? {
    return nbNullSafe(value1) { v1 ->
        if (v1.isNotEmpty()) {
            transform(v1)
        } else {
            null
        }
    }
}

inline fun <T1, T2, R> nbNullSafe(
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

inline fun <T1, T2, R> nbNullSafeList(
    value1: List<T1>?,
    value2: List<T2>?,
    transform: (value1: List<T1>, value2: List<T2>) -> R?
): R? {
    return nbNullSafe(value1, value2) { v1, v2 ->
        if (v1.isNotEmpty() && v2.isNotEmpty()) {
            transform(v1, v2)
        } else {
            null
        }
    }
}

inline fun <T1, T2, T3, R> nbNullSafe(
    value1: T1?,
    value2: T2?,
    value3: T3?,
    transform: (value1: T1, value2: T2, value3: T3) -> R?
): R? {
    return if (value1 != null && value2 != null && value3 != null) {
        transform(value1, value2, value3)
    } else {
        null
    }
}

inline fun <T1, T2, T3, T4, R> nbNullSafe(
    value1: T1?,
    value2: T2?,
    value3: T3?,
    value4: T4?,
    transform: (value1: T1, value2: T2, value3: T3, value4: T4) -> R?
): R? {
    return if (value1 != null && value2 != null && value3 != null && value4 != null) {
        transform(value1, value2, value3, value4)
    } else {
        null
    }
}
