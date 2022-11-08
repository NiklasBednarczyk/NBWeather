package de.niklasbednarczyk.openweathermap.data.onecall.values.weather

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString

@JvmInline
value class WeatherDescriptionValue private constructor(val value: OwmString) {

    companion object {

        internal fun from(description: String?): WeatherDescriptionValue? {
            val value = OwmString.Value.from(description?.replaceFirstChar(Char::titlecase))
            return owmNullSafe(value) { WeatherDescriptionValue(it) }
        }

    }

}