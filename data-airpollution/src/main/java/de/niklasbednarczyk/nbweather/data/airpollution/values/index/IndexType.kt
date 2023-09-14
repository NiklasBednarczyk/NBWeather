package de.niklasbednarczyk.nbweather.data.airpollution.values.index

enum class IndexType {

    GOOD,
    FAIR,
    MODERATE,
    POOR,
    VERY_POOR;

    internal companion object {

        fun from(value: Number, bounds: IndexBoundsModel): IndexType? {
            return when (value.toLong()) {
                in 0..<bounds.lowerBoundIndex2 -> GOOD
                in bounds.lowerBoundIndex2..<bounds.lowerBoundIndex3 -> FAIR
                in bounds.lowerBoundIndex3..<bounds.lowerBoundIndex4 -> MODERATE
                in bounds.lowerBoundIndex4..<bounds.lowerBoundIndex5 -> POOR
                in bounds.lowerBoundIndex5..<Long.MAX_VALUE -> VERY_POOR
                else -> null
            }
        }

    }

}