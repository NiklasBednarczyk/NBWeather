package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.types.wind.WindDegreesType

@JvmInline
value class WindDegreesForecastValue private constructor(private val value: Long) : ForecastValue {

    val type: WindDegreesType?
        get() = WindDegreesType.from(value)

    val rotationDegrees: Float
        get() = value - 180f

    internal companion object {

        fun from(value: Long?): WindDegreesForecastValue? {
            return nbNullSafe(value) { WindDegreesForecastValue(it) }
        }

    }

}