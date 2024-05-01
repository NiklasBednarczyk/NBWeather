package de.niklasbednarczyk.nbweather.core.common.coordinates

fun areCoordinatesApproximatelyTheSame(
    latitude1: Double?,
    longitude1: Double?,
    latitude2: Double,
    longitude2: Double
): Boolean {
    val latitude1Int = latitude1?.toInt()
    val longitude1Int = longitude1?.toInt()
    val latitude2Int = latitude2.toInt()
    val longitude2Int = longitude2.toInt()
    return latitude1Int == latitude2Int && longitude1Int == longitude2Int
}