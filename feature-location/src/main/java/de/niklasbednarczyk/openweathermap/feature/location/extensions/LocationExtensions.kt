package de.niklasbednarczyk.openweathermap.feature.location.extensions

import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.onecall.values.weather.WeatherIconType
import de.niklasbednarczyk.openweathermap.data.onecall.values.winddegrees.WindDegreesType

val WeatherIconType.icon: OwmIconModel
    get() = when (this) {
        WeatherIconType.D_CLEAR_SKY -> OwmIcons.WeatherDayClearSky
        WeatherIconType.D_FEW_CLOUDS -> OwmIcons.WeatherDayFewClouds
        WeatherIconType.D_SCATTERED_CLOUDS -> OwmIcons.WeatherDayScatteredClouds
        WeatherIconType.D_BROKEN_CLOUDS -> OwmIcons.WeatherDayBrokenClouds
        WeatherIconType.D_SHOWER_RAIN -> OwmIcons.WeatherDayShowerRain
        WeatherIconType.D_RAIN -> OwmIcons.WeatherDayRain
        WeatherIconType.D_THUNDERSTORM -> OwmIcons.WeatherDayThunderstorm
        WeatherIconType.D_SNOW -> OwmIcons.WeatherDaySnow
        WeatherIconType.D_MIST -> OwmIcons.WeatherDayMist
        WeatherIconType.N_CLEAR_SKY -> OwmIcons.WeatherNightClearSky
        WeatherIconType.N_FEW_CLOUDS -> OwmIcons.WeatherNightFewClouds
        WeatherIconType.N_SCATTERED_CLOUDS -> OwmIcons.WeatherNightScatteredClouds
        WeatherIconType.N_BROKEN_CLOUDS -> OwmIcons.WeatherNightBrokenClouds
        WeatherIconType.N_SHOWER_RAIN -> OwmIcons.WeatherNightShowerRain
        WeatherIconType.N_RAIN -> OwmIcons.WeatherNightRain
        WeatherIconType.N_THUNDERSTORM -> OwmIcons.WeatherNightThunderstorm
        WeatherIconType.N_SNOW -> OwmIcons.WeatherNightSnow
        WeatherIconType.N_MIST -> OwmIcons.WeatherNightMist
    }

val WindDegreesType.displayText: OwmString
    get() {
        val resId = when (this) {
            WindDegreesType.N -> R.string.screen_location_wind_degrees_value_n
            WindDegreesType.NNE -> R.string.screen_location_wind_degrees_value_nne
            WindDegreesType.NE -> R.string.screen_location_wind_degrees_value_ne
            WindDegreesType.ENE -> R.string.screen_location_wind_degrees_value_ene
            WindDegreesType.E -> R.string.screen_location_wind_degrees_value_e
            WindDegreesType.ESE -> R.string.screen_location_wind_degrees_value_ese
            WindDegreesType.SE -> R.string.screen_location_wind_degrees_value_se
            WindDegreesType.SSE -> R.string.screen_location_wind_degrees_value_sse
            WindDegreesType.S -> R.string.screen_location_wind_degrees_value_s
            WindDegreesType.SSW -> R.string.screen_location_wind_degrees_value_ssw
            WindDegreesType.SW -> R.string.screen_location_wind_degrees_value_sw
            WindDegreesType.WSW -> R.string.screen_location_wind_degrees_value_wsw
            WindDegreesType.W -> R.string.screen_location_wind_degrees_value_w
            WindDegreesType.WNW -> R.string.screen_location_wind_degrees_value_wnw
            WindDegreesType.NW -> R.string.screen_location_wind_degrees_value_nw
            WindDegreesType.NNW -> R.string.screen_location_wind_degrees_value_nnw
        }
        return OwmString.Resource(resId)
    }