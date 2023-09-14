package de.niklasbednarczyk.nbweather.core.common.nullsafe

inline fun <T, R> Iterable<T>?.nbMap(
    transform: (T) -> R
): List<R> {
    return this?.map(transform) ?: emptyList()
}

inline fun <T, R: Any> Iterable<T>?.nbMapNotNull(
    transform: (T) -> R?
): List<R> {
    return this?.mapNotNull(transform) ?: emptyList()
}