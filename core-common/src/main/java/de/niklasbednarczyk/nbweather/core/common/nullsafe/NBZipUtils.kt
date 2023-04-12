package de.niklasbednarczyk.nbweather.core.common.nullsafe


inline fun <T1, T2, T3> nbZip(
    list1: List<T1>?,
    list2: List<T2>?,
    transform: (value1: T1, value2: T2) -> T3?
): List<T3?>? {
    return list1?.zip(list2 ?: emptyList(), transform)
}