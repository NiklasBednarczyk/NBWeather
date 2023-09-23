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

inline fun <T1, T2, T3, T4, T5, R> nbNullSafe(
    value1: T1?,
    value2: T2?,
    value3: T3?,
    value4: T4?,
    value5: T5?,
    transform: (value1: T1, value2: T2, value3: T3, value4: T4, value5: T5) -> R?
): R? {
    return if (value1 != null && value2 != null && value3 != null && value4 != null && value5 != null) {
        transform(value1, value2, value3, value4, value5)
    } else {
        null
    }
}

inline fun <T1, T2, T3, T4, T5, T6, R> nbNullSafe(
    value1: T1?,
    value2: T2?,
    value3: T3?,
    value4: T4?,
    value5: T5?,
    value6: T6?,
    transform: (value1: T1, value2: T2, value3: T3, value4: T4, value5: T5, value6: T6) -> R?
): R? {
    return if (value1 != null && value2 != null && value3 != null && value4 != null && value5 != null && value6 != null) {
        transform(value1, value2, value3, value4, value5, value6)
    } else {
        null
    }
}

inline fun <T1, T2, T3, T4, T5, T6, T7, R> nbNullSafe(
    value1: T1?,
    value2: T2?,
    value3: T3?,
    value4: T4?,
    value5: T5?,
    value6: T6?,
    value7: T7?,
    transform: (value1: T1, value2: T2, value3: T3, value4: T4, value5: T5, value6: T6, value7: T7) -> R?
): R? {
    return if (value1 != null && value2 != null && value3 != null && value4 != null && value5 != null && value6 != null && value7 != null) {
        transform(value1, value2, value3, value4, value5, value6, value7)
    } else {
        null
    }
}

inline fun <T1, T2, T3, T4, T5, T6, T7, T8, R> nbNullSafe(
    value1: T1?,
    value2: T2?,
    value3: T3?,
    value4: T4?,
    value5: T5?,
    value6: T6?,
    value7: T7?,
    value8: T8?,
    transform: (value1: T1, value2: T2, value3: T3, value4: T4, value5: T5, value6: T6, value7: T7, value8: T8) -> R?
): R? {
    return if (value1 != null && value2 != null && value3 != null && value4 != null && value5 != null && value6 != null && value7 != null && value8 != null) {
        transform(value1, value2, value3, value4, value5, value6, value7, value8)
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