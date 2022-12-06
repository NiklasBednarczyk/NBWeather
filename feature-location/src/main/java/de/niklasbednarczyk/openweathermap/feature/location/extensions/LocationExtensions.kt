package de.niklasbednarczyk.openweathermap.feature.location.extensions

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.onecall.values.moon.MoonPhaseType
import de.niklasbednarczyk.openweathermap.data.onecall.values.weather.WeatherIconType
import de.niklasbednarczyk.openweathermap.data.onecall.values.winddegrees.WindDegreesType

val MoonPhaseType.displayText: OwmString
    get() {
        val resId = when (this) {
            MoonPhaseType.NEW_MOON -> R.string.screen_location_overview_today_sun_and_moon_moon_phase_new_moon
            MoonPhaseType.WAXING_CRESCENT_1,
            MoonPhaseType.WAXING_CRESCENT_2,
            MoonPhaseType.WAXING_CRESCENT_3,
            MoonPhaseType.WAXING_CRESCENT_4,
            MoonPhaseType.WAXING_CRESCENT_5,
            MoonPhaseType.WAXING_CRESCENT_6 -> R.string.screen_location_overview_today_sun_and_moon_moon_phase_waxing_crescent
            MoonPhaseType.FIRST_QUARTER_MOON -> R.string.screen_location_overview_today_sun_and_moon_moon_phase_first_quarter_moon
            MoonPhaseType.WAXING_GIBBOUS_1,
            MoonPhaseType.WAXING_GIBBOUS_2,
            MoonPhaseType.WAXING_GIBBOUS_3,
            MoonPhaseType.WAXING_GIBBOUS_4,
            MoonPhaseType.WAXING_GIBBOUS_5,
            MoonPhaseType.WAXING_GIBBOUS_6 -> R.string.screen_location_overview_today_sun_and_moon_moon_phase_waxing_gibbous
            MoonPhaseType.FULL_MOON -> R.string.screen_location_overview_today_sun_and_moon_moon_phase_full_moon
            MoonPhaseType.WANING_GIBBOUS_1,
            MoonPhaseType.WANING_GIBBOUS_2,
            MoonPhaseType.WANING_GIBBOUS_3,
            MoonPhaseType.WANING_GIBBOUS_4,
            MoonPhaseType.WANING_GIBBOUS_5,
            MoonPhaseType.WANING_GIBBOUS_6 -> R.string.screen_location_overview_today_sun_and_moon_moon_phase_waning_gibbous
            MoonPhaseType.LAST_QUARTER_MOON -> R.string.screen_location_overview_today_sun_and_moon_moon_phase_last_quarter_moon
            MoonPhaseType.WANING_CRESCENT_1,
            MoonPhaseType.WANING_CRESCENT_2,
            MoonPhaseType.WANING_CRESCENT_3,
            MoonPhaseType.WANING_CRESCENT_4,
            MoonPhaseType.WANING_CRESCENT_5,
            MoonPhaseType.WANING_CRESCENT_6 -> R.string.screen_location_overview_today_sun_and_moon_moon_phase_waning_crescent
        }
        return OwmString.Resource(resId)
    }

val MoonPhaseType.icon: OwmIconModel
    get() = when (this) {
        MoonPhaseType.NEW_MOON -> OwmIcons.MoonPhaseNew
        MoonPhaseType.WAXING_CRESCENT_1 -> OwmIcons.MoonPhaseWaxingCrescent1
        MoonPhaseType.WAXING_CRESCENT_2 -> OwmIcons.MoonPhaseWaxingCrescent2
        MoonPhaseType.WAXING_CRESCENT_3 -> OwmIcons.MoonPhaseWaxingCrescent3
        MoonPhaseType.WAXING_CRESCENT_4 -> OwmIcons.MoonPhaseWaxingCrescent4
        MoonPhaseType.WAXING_CRESCENT_5 -> OwmIcons.MoonPhaseWaxingCrescent5
        MoonPhaseType.WAXING_CRESCENT_6 -> OwmIcons.MoonPhaseWaxingCrescent6
        MoonPhaseType.FIRST_QUARTER_MOON -> OwmIcons.MoonPhaseFirstQuarter
        MoonPhaseType.WAXING_GIBBOUS_1 -> OwmIcons.MoonPhaseWaxingGibbous1
        MoonPhaseType.WAXING_GIBBOUS_2 -> OwmIcons.MoonPhaseWaxingGibbous2
        MoonPhaseType.WAXING_GIBBOUS_3 -> OwmIcons.MoonPhaseWaxingGibbous3
        MoonPhaseType.WAXING_GIBBOUS_4 -> OwmIcons.MoonPhaseWaxingGibbous4
        MoonPhaseType.WAXING_GIBBOUS_5 -> OwmIcons.MoonPhaseWaxingGibbous5
        MoonPhaseType.WAXING_GIBBOUS_6 -> OwmIcons.MoonPhaseWaxingGibbous6
        MoonPhaseType.FULL_MOON -> OwmIcons.MoonPhaseFull
        MoonPhaseType.WANING_GIBBOUS_1 -> OwmIcons.MoonPhaseWaningGibbous1
        MoonPhaseType.WANING_GIBBOUS_2 -> OwmIcons.MoonPhaseWaningGibbous2
        MoonPhaseType.WANING_GIBBOUS_3 -> OwmIcons.MoonPhaseWaningGibbous3
        MoonPhaseType.WANING_GIBBOUS_4 -> OwmIcons.MoonPhaseWaningGibbous4
        MoonPhaseType.WANING_GIBBOUS_5 -> OwmIcons.MoonPhaseWaningGibbous5
        MoonPhaseType.WANING_GIBBOUS_6 -> OwmIcons.MoonPhaseWaningGibbous6
        MoonPhaseType.LAST_QUARTER_MOON -> OwmIcons.MoonPhaseLastQuarter
        MoonPhaseType.WANING_CRESCENT_1 -> OwmIcons.MoonPhaseWaningCrescent1
        MoonPhaseType.WANING_CRESCENT_2 -> OwmIcons.MoonPhaseWaningCrescent2
        MoonPhaseType.WANING_CRESCENT_3 -> OwmIcons.MoonPhaseWaningCrescent3
        MoonPhaseType.WANING_CRESCENT_4 -> OwmIcons.MoonPhaseWaningCrescent4
        MoonPhaseType.WANING_CRESCENT_5 -> OwmIcons.MoonPhaseWaningCrescent5
        MoonPhaseType.WANING_CRESCENT_6 -> OwmIcons.MoonPhaseWaningCrescent6
    }

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