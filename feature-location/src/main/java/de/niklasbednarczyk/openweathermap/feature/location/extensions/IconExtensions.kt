package de.niklasbednarczyk.openweathermap.feature.location.extensions

import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.onecall.values.weather.WeatherIconType

fun WeatherIconType.getIcon(): OwmIconModel {
    return when (this) {
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
}