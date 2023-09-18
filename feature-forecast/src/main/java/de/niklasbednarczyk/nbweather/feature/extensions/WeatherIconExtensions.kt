package de.niklasbednarczyk.nbweather.feature.extensions

import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherIconType

val WeatherIconType.icon: NBIconModel
    get() = when (this) {
        WeatherIconType.D_CLEAR_SKY -> NBIcons.WeatherDayClearSky
        WeatherIconType.D_FEW_CLOUDS -> NBIcons.WeatherDayFewClouds
        WeatherIconType.D_SCATTERED_CLOUDS -> NBIcons.WeatherDayScatteredClouds
        WeatherIconType.D_BROKEN_CLOUDS -> NBIcons.WeatherDayBrokenClouds
        WeatherIconType.D_SHOWER_RAIN -> NBIcons.WeatherDayShowerRain
        WeatherIconType.D_RAIN -> NBIcons.WeatherDayRain
        WeatherIconType.D_THUNDERSTORM -> NBIcons.WeatherDayThunderstorm
        WeatherIconType.D_SNOW -> NBIcons.WeatherDaySnow
        WeatherIconType.D_MIST -> NBIcons.WeatherDayMist
        WeatherIconType.N_CLEAR_SKY -> NBIcons.WeatherNightClearSky
        WeatherIconType.N_FEW_CLOUDS -> NBIcons.WeatherNightFewClouds
        WeatherIconType.N_SCATTERED_CLOUDS -> NBIcons.WeatherNightScatteredClouds
        WeatherIconType.N_BROKEN_CLOUDS -> NBIcons.WeatherNightBrokenClouds
        WeatherIconType.N_SHOWER_RAIN -> NBIcons.WeatherNightShowerRain
        WeatherIconType.N_RAIN -> NBIcons.WeatherNightRain
        WeatherIconType.N_THUNDERSTORM -> NBIcons.WeatherNightThunderstorm
        WeatherIconType.N_SNOW -> NBIcons.WeatherNightSnow
        WeatherIconType.N_MIST -> NBIcons.WeatherNightMist
    }