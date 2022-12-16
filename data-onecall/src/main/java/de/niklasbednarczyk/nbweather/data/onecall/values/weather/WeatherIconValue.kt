package de.niklasbednarczyk.nbweather.data.onecall.values.weather

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

@JvmInline
value class WeatherIconValue private constructor(val type: WeatherIconType) {

    companion object {

        internal fun from(icon: String?): WeatherIconValue? {
            val type = WeatherIconType.from(icon)
            return nbNullSafe(type) { WeatherIconValue(it) }
        }

    }

}