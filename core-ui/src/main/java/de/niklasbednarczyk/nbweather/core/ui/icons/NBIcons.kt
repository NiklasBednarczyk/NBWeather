package de.niklasbednarczyk.nbweather.core.ui.icons

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R

object NBIcons {

    data object About : NBIconItem {
        override val resId: Int = R.drawable.baseline_info_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_about)
    }

    data object Appearance : NBIconItem {
        override val resId: Int = R.drawable.baseline_palette_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_appearance)
    }

    data object Back : NBIconItem {
        override val resId: Int = R.drawable.baseline_arrow_back_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_back)
    }

    data object Cancel : NBIconItem {
        override val resId: Int = R.drawable.baseline_close_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_cancel)
    }

    data object Cloudiness : NBIconItem {
        override val resId: Int = R.drawable.baseline_cloud_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_cloudiness)
    }

    data object Delete : NBIconItem {
        override val resId: Int = R.drawable.baseline_delete_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_delete)
    }

    data object DewPoint : NBIconItem {
        override val resId: Int = R.drawable.wi_raindrops
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_dew_point)
    }

    data object DragAndDrop : NBIconItem {
        override val resId: Int = R.drawable.baseline_drag_indicator_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_drag_and_drop)
    }

    data object Drawer : NBIconItem {
        override val resId: Int = R.drawable.baseline_menu_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_drawer)
    }

    data object ErrorNoInternet : NBIconItem {
        override val resId: Int = R.drawable.baseline_cloud_off_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_error_no_internet)
    }

    data object ErrorUnknown : NBIconItem {
        override val resId: Int = R.drawable.baseline_error_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_error_unknown)
    }

    data object FeelsLike : NBIconItem {
        override val resId: Int = R.drawable.wi_thermometer
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_feels_like)
    }

    data object FindLocation : NBIconItem {
        override val resId: Int = R.drawable.baseline_my_location_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_find_location)
    }

    data object Font : NBIconItem {
        override val resId: Int = R.drawable.baseline_font_download_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_font)
    }

    data object GitHub : NBIconItem {
        override val resId: Int = R.drawable.github_mark
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_git_hub)
    }

    data object Humidity : NBIconItem {
        override val resId: Int = R.drawable.wi_humidity
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_humidity)
    }

    data object Location : NBIconItem {
        override val resId: Int = R.drawable.baseline_place_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_location)
    }

    data object MaxTemperature : NBIconItem {
        override val resId: Int = R.drawable.baseline_vertical_align_top_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_max_temperature)
    }

    data object MinTemperature : NBIconItem {
        override val resId: Int = R.drawable.baseline_vertical_align_bottom_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_min_temperature)
    }

    data object MoonPhaseFirstQuarter : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_first_quarter
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_first_quarter_moon)
    }

    data object MoonPhaseFull : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_full
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_full_moon)
    }

    data object MoonPhaseLastQuarter : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_third_quarter
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_last_quarter_moon)
    }

    data object MoonPhaseNew : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_new
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_new_moon)
    }

    data object MoonPhaseWaningCrescent1 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waning_crescent_1
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waning_crescent_1)
    }

    data object MoonPhaseWaningCrescent2 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waning_crescent_2
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waning_crescent_2)
    }

    data object MoonPhaseWaningCrescent3 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waning_crescent_3
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waning_crescent_3)
    }

    data object MoonPhaseWaningCrescent4 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waning_crescent_4
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waning_crescent_4)
    }

    data object MoonPhaseWaningCrescent5 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waning_crescent_5
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waning_crescent_5)
    }

    data object MoonPhaseWaningCrescent6 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waning_crescent_6
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waning_crescent_6)
    }

    data object MoonPhaseWaningGibbous1 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waning_gibbous_1
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waning_gibbous_1)
    }

    data object MoonPhaseWaningGibbous2 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waning_gibbous_2
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waning_gibbous_2)
    }

    data object MoonPhaseWaningGibbous3 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waning_gibbous_3
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waning_gibbous_3)
    }

    data object MoonPhaseWaningGibbous4 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waning_gibbous_4
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waning_gibbous_4)
    }

    data object MoonPhaseWaningGibbous5 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waning_gibbous_5
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waning_gibbous_5)
    }

    data object MoonPhaseWaningGibbous6 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waning_gibbous_6
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waning_gibbous_6)
    }

    data object MoonPhaseWaxingCrescent1 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waxing_crescent_1
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waxing_crescent_1)
    }

    data object MoonPhaseWaxingCrescent2 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waxing_crescent_2
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waxing_crescent_2)
    }

    data object MoonPhaseWaxingCrescent3 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waxing_crescent_3
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waxing_crescent_3)
    }

    data object MoonPhaseWaxingCrescent4 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waxing_crescent_4
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waxing_crescent_4)
    }

    data object MoonPhaseWaxingCrescent5 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waxing_crescent_5
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waxing_crescent_5)
    }

    data object MoonPhaseWaxingCrescent6 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waxing_crescent_6
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waxing_crescent_6)
    }

    data object MoonPhaseWaxingGibbous1 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waxing_gibbous_1
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waxing_gibbous_1)
    }

    data object MoonPhaseWaxingGibbous2 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waxing_gibbous_2
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waxing_gibbous_2)
    }

    data object MoonPhaseWaxingGibbous3 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waxing_gibbous_3
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waxing_gibbous_3)
    }

    data object MoonPhaseWaxingGibbous4 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waxing_gibbous_4
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waxing_gibbous_4)
    }

    data object MoonPhaseWaxingGibbous5 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waxing_gibbous_5
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waxing_gibbous_5)
    }

    data object MoonPhaseWaxingGibbous6 : NBIconItem {
        override val resId: Int = R.drawable.wi_moon_alt_waxing_gibbous_6
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moon_phase_waxing_gibbous_6)
    }

    data object Moonrise : NBIconItem {
        override val resId: Int = R.drawable.wi_moonrise
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moonrise)
    }

    data object Moonset : NBIconItem {
        override val resId: Int = R.drawable.wi_moonset
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_moonset)
    }

    data object Order : NBIconItem {
        override val resId: Int = R.drawable.baseline_drag_handle_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_order)
    }

    data object Pressure : NBIconItem {
        override val resId: Int = R.drawable.wi_barometer
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_pressure)
    }

    data object ProbabilityOfPrecipitation : NBIconItem {
        override val resId: Int = R.drawable.wi_umbrella
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_probability_of_precipitation)
    }

    data object Rain : NBIconItem {
        override val resId: Int = R.drawable.wi_raindrop
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_rain)
    }

    data object Reset : NBIconItem {
        override val resId: Int = R.drawable.baseline_restart_alt_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_reset)
    }

    data object Search : NBIconItem {
        override val resId: Int = R.drawable.baseline_search_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_search)
    }

    data object Settings : NBIconItem {
        override val resId: Int = R.drawable.baseline_settings_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_settings)
    }

    data object Snow : NBIconItem {
        override val resId: Int = R.drawable.wi_snowflake_cold
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_snow)
    }

    data object Sun : NBIconItem {
        override val resId: Int = R.drawable.baseline_wb_sunny_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_sun)
    }

    data object Sunrise : NBIconItem {
        override val resId: Int = R.drawable.wi_sunrise
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_sunrise)
    }

    data object Sunset : NBIconItem {
        override val resId: Int = R.drawable.wi_sunset
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_sunset)
    }

    data object Temperature : NBIconItem {
        override val resId: Int = R.drawable.baseline_thermostat_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_temperature)
    }

    data object Units : NBIconItem {
        override val resId: Int = R.drawable.baseline_square_foot_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_units)
    }

    data object UVIndex : NBIconItem {
        override val resId: Int = R.drawable.baseline_light_mode_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_uv_index)
    }

    data object Visibility : NBIconItem {
        override val resId: Int = R.drawable.baseline_visibility_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_visibility)
    }

    data object Warning : NBIconItem {
        override val resId: Int = R.drawable.baseline_warning_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_warning)
    }

    data object WeatherDayBrokenClouds : NBIconItem {
        override val resId: Int = R.drawable.wi_cloudy
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_day_broken_clouds)
    }

    data object WeatherDayClearSky : NBIconItem {
        override val resId: Int = R.drawable.wi_day_sunny
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_day_clear_sky)
    }

    data object WeatherDayFewClouds : NBIconItem {
        override val resId: Int = R.drawable.wi_day_cloudy
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_day_few_clouds)
    }

    data object WeatherDayMist : NBIconItem {
        override val resId: Int = R.drawable.wi_day_fog
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_day_mist)
    }

    data object WeatherDayRain : NBIconItem {
        override val resId: Int = R.drawable.wi_day_rain
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_day_rain)
    }

    data object WeatherDayScatteredClouds : NBIconItem {
        override val resId: Int = R.drawable.wi_cloud
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_day_scattered_clouds)
    }

    data object WeatherDayShowerRain : NBIconItem {
        override val resId: Int = R.drawable.wi_day_showers
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_day_shower_rain)
    }

    data object WeatherDaySnow : NBIconItem {
        override val resId: Int = R.drawable.wi_day_snow
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_day_snow)
    }

    data object WeatherDayThunderstorm : NBIconItem {
        override val resId: Int = R.drawable.wi_day_lightning
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_day_thunderstorm)
    }

    data object WeatherNightBrokenClouds : NBIconItem {
        override val resId: Int = R.drawable.wi_cloudy
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_night_broken_clouds)
    }

    data object WeatherNightClearSky : NBIconItem {
        override val resId: Int = R.drawable.wi_night_clear
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_night_clear_sky)
    }

    data object WeatherNightFewClouds : NBIconItem {
        override val resId: Int = R.drawable.wi_night_alt_cloudy
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_night_few_clouds)
    }

    data object WeatherNightMist : NBIconItem {
        override val resId: Int = R.drawable.wi_night_fog
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_night_mist)
    }

    data object WeatherNightRain : NBIconItem {
        override val resId: Int = R.drawable.wi_night_alt_rain
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_night_rain)
    }

    data object WeatherNightScatteredClouds : NBIconItem {
        override val resId: Int = R.drawable.wi_cloud
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_night_scattered_clouds)
    }

    data object WeatherNightShowerRain : NBIconItem {
        override val resId: Int = R.drawable.wi_night_alt_showers
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_night_shower_rain)
    }

    data object WeatherNightSnow : NBIconItem {
        override val resId: Int = R.drawable.wi_night_alt_snow
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_night_snow)
    }

    data object WeatherNightThunderstorm : NBIconItem {
        override val resId: Int = R.drawable.wi_night_alt_lightning
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_weather_night_thunderstorm)
    }

    data object Website : NBIconItem {
        override val resId: Int = R.drawable.baseline_link_24
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_website)
    }

    data object WindDirection : NBIconItem {
        override val resId: Int = R.drawable.wi_wind_deg
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_wind_direction)
    }

    data object WindGust : NBIconItem {
        override val resId: Int = R.drawable.wi_strong_wind
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_wind_gust)
    }

    data object WindSpeed : NBIconItem {
        override val resId: Int = R.drawable.wi_windy
        override val contentDescription: NBString =
            NBString.ResString(R.string.icon_content_description_wind_speed)
    }

}