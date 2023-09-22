package de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

@JvmInline
value class WindDegreesValue private constructor(private val value: Long) {

    val type: WindDegreesType?
        get() = WindDegreesType.from(value)

    val rotationDegrees: Float
        get() = value - 180f

    internal companion object {

        fun from(value: Long?): WindDegreesValue? {
            return nbNullSafe(value) { WindDegreesValue(it) }
        }

    }

}