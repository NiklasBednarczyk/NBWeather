package de.niklasbednarczyk.nbweather.core.common.nullsafe

inline fun <T, R> Iterable<T>?.nbMap(
    transform: (T) -> R
): List<R> {
    return this?.map(transform) ?: emptyList()
}
