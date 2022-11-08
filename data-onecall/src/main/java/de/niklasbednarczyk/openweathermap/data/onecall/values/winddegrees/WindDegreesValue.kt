package de.niklasbednarczyk.openweathermap.data.onecall.values.winddegrees

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe

@JvmInline
value class WindDegreesValue private constructor(val type: WindDegreesType) {

    companion object {

        internal fun from(value: Long?): WindDegreesValue? {
            val type = WindDegreesType.from(value)
            return owmNullSafe(type) { WindDegreesValue(it) }
        }

    }

}