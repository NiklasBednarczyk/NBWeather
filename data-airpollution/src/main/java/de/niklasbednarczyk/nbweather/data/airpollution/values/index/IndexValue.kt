package de.niklasbednarczyk.nbweather.data.airpollution.values.index

interface IndexValue {

    val value: Number

    val bounds: IndexBoundsModel

    val type: IndexType?
        get() = IndexType.from(value, bounds)

}