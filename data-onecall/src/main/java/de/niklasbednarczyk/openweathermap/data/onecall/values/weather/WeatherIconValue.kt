package de.niklasbednarczyk.openweathermap.data.onecall.values.weather

@JvmInline
value class WeatherIconValue private constructor(val type: WeatherIconType) {

    companion object {

        internal fun from(icon: String?): WeatherIconValue? {
            val type = WeatherIconType.from(icon)
            return if (type != null) WeatherIconValue(type) else null
        }

    }

}