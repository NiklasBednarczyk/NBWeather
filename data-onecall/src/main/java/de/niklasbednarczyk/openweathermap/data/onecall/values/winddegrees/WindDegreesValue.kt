package de.niklasbednarczyk.openweathermap.data.onecall.values.winddegrees

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe

@JvmInline
value class WindDegreesValue private constructor(private val value: Long) {

    val type: WindDegreesType?
        get() = WindDegreesType.from(value)

    val rotationDegrees: Float
        get() = value.minus(180f)

    companion object {

        internal fun from(value: Long?): WindDegreesValue? {
            return owmNullSafe(value) { WindDegreesValue(it) }
        }

    }

}