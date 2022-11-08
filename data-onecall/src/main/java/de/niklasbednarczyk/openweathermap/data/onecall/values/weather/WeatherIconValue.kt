package de.niklasbednarczyk.openweathermap.data.onecall.values.weather

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe

@JvmInline
value class WeatherIconValue private constructor(val type: WeatherIconType) {

    companion object {

        internal fun from(icon: String?): WeatherIconValue? {
            val type = WeatherIconType.from(icon)
            return owmNullSafe(type) { WeatherIconValue(it) }
        }

    }

}