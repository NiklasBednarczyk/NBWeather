package de.niklasbednarczyk.nbweather.data.onecall.values.weather

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString

@JvmInline
value class WeatherDescriptionValue private constructor(val value: NBString) {

    companion object {

        internal fun from(description: String?): WeatherDescriptionValue? {
            val value = NBString.Value.from(description?.replaceFirstChar(Char::titlecase))
            return nbNullSafe(value) { WeatherDescriptionValue(it) }
        }

    }

}