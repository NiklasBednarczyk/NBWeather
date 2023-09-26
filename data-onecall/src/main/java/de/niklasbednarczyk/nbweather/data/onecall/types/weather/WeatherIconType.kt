package de.niklasbednarczyk.nbweather.data.onecall.types.weather

enum class WeatherIconType {

    D_CLEAR_SKY,
    D_FEW_CLOUDS,
    D_SCATTERED_CLOUDS,
    D_BROKEN_CLOUDS,
    D_SHOWER_RAIN,
    D_RAIN,
    D_THUNDERSTORM,
    D_SNOW,
    D_MIST,
    N_CLEAR_SKY,
    N_FEW_CLOUDS,
    N_SCATTERED_CLOUDS,
    N_BROKEN_CLOUDS,
    N_SHOWER_RAIN,
    N_RAIN,
    N_THUNDERSTORM,
    N_SNOW,
    N_MIST;

    private val remoteId: String
        get() = when (this) {
            D_CLEAR_SKY -> "01d"
            D_FEW_CLOUDS -> "02d"
            D_SCATTERED_CLOUDS -> "03d"
            D_BROKEN_CLOUDS -> "04d"
            D_SHOWER_RAIN -> "09d"
            D_RAIN -> "10d"
            D_THUNDERSTORM -> "11d"
            D_SNOW -> "13d"
            D_MIST -> "50d"
            N_CLEAR_SKY -> "01n"
            N_FEW_CLOUDS -> "02n"
            N_SCATTERED_CLOUDS -> "03n"
            N_BROKEN_CLOUDS -> "04n"
            N_SHOWER_RAIN -> "09n"
            N_RAIN -> "10n"
            N_THUNDERSTORM -> "11n"
            N_SNOW -> "13n"
            N_MIST -> "50n"
        }

    internal companion object {

        fun from(icon: String?): WeatherIconType? {
            return values().find { value -> value.remoteId == icon }
        }

    }

}