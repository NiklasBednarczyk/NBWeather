package de.niklasbednarczyk.nbweather.core.ui.icons

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R

object NBIcons {

    object About : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_info_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_about)
    }

    object Back : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_arrow_back_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_back)
    }

    object Cancel : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_close_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_cancel)
    }

    object Cloudiness : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_cloud_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_cloudiness)
    }

    object ColorScheme : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_palette_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_color_scheme)
    }

    object Daily : NBIconModel.FilledAndOutlined {
        override val resIdFilled: Int
            get() = R.drawable.ic_baseline_date_range_24
        override val resIdOutlined: Int
            get() = R.drawable.ic_outline_date_range_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_daily)
    }

    object Delete : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_delete_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_delete)
    }

    object DewPoint : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_thermometer
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_dew_point)
    }

    object Drawer : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_menu_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_drawer)
    }

    object Email : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_email_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_email)
    }

    object ErrorNoInternet : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_cloud_off_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_error_no_internet)
    }

    object ErrorUnknown : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_error_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_error_unknown)
    }

    object Expand : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_expand_more_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_expand)
    }

    object FindLocation : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_my_location_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_find_location)
    }

    object GitHub : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.github_mark
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_github)
    }

    object Hourly : NBIconModel.FilledAndOutlined {
        override val resIdFilled: Int
            get() = R.drawable.ic_baseline_watch_later_24
        override val resIdOutlined: Int
            get() = R.drawable.ic_outline_watch_later_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_hourly)
    }

    object Humidity : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_humidity
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_humidity)
    }

    object Language : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_translate_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_language)
    }

    object Location : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_place_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_location)
    }

    object MoonPhaseFirstQuarter : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_first_quarter
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_first_quarter_moon)
    }

    object MoonPhaseFull : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_full
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_full_moon)
    }

    object MoonPhaseLastQuarter : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_third_quarter
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_last_quarter_moon)
    }

    object MoonPhaseNew : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_new
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_new_moon)
    }

    object MoonPhaseWaningCrescent1 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waning_crescent_1
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waning_crescent_1)
    }

    object MoonPhaseWaningCrescent2 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waning_crescent_2
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waning_crescent_2)
    }

    object MoonPhaseWaningCrescent3 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waning_crescent_3
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waning_crescent_3)
    }

    object MoonPhaseWaningCrescent4 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waning_crescent_4
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waning_crescent_4)
    }

    object MoonPhaseWaningCrescent5 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waning_crescent_5
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waning_crescent_5)
    }

    object MoonPhaseWaningCrescent6 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waning_crescent_6
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waning_crescent_6)
    }

    object MoonPhaseWaningGibbous1 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waning_gibbous_1
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waning_gibbous_1)
    }

    object MoonPhaseWaningGibbous2 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waning_gibbous_2
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waning_gibbous_2)
    }

    object MoonPhaseWaningGibbous3 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waning_gibbous_3
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waning_gibbous_3)
    }

    object MoonPhaseWaningGibbous4 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waning_gibbous_4
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waning_gibbous_4)
    }

    object MoonPhaseWaningGibbous5 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waning_gibbous_5
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waning_gibbous_5)
    }

    object MoonPhaseWaningGibbous6 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waning_gibbous_6
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waning_gibbous_6)
    }

    object MoonPhaseWaxingCrescent1 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waxing_crescent_1
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waxing_crescent_1)
    }

    object MoonPhaseWaxingCrescent2 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waxing_crescent_2
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waxing_crescent_2)
    }

    object MoonPhaseWaxingCrescent3 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waxing_crescent_3
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waxing_crescent_3)
    }

    object MoonPhaseWaxingCrescent4 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waxing_crescent_4
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waxing_crescent_4)
    }

    object MoonPhaseWaxingCrescent5 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waxing_crescent_5
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waxing_crescent_5)
    }

    object MoonPhaseWaxingCrescent6 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waxing_crescent_6
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waxing_crescent_6)
    }

    object MoonPhaseWaxingGibbous1 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waxing_gibbous_1
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waxing_gibbous_1)
    }

    object MoonPhaseWaxingGibbous2 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waxing_gibbous_2
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waxing_gibbous_2)
    }

    object MoonPhaseWaxingGibbous3 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waxing_gibbous_3
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waxing_gibbous_3)
    }

    object MoonPhaseWaxingGibbous4 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waxing_gibbous_4
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waxing_gibbous_4)
    }

    object MoonPhaseWaxingGibbous5 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waxing_gibbous_5
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waxing_gibbous_5)
    }

    object MoonPhaseWaxingGibbous6 : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moon_alt_waxing_gibbous_6
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moon_phase_waxing_gibbous_6)
    }

    object Moonrise : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moonrise
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moonrise)
    }

    object Moonset : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_moonset
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_moonset)
    }

    object Pressure : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_barometer
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_pressure)
    }

    object ProbabilityOfPrecipitation : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_umbrella
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_probability_of_precipitation)
    }

    object Rain : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_raindrop
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_rain)
    }

    object Search : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_search_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_search)
    }

    object Settings : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_settings_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_settings)
    }

    object Snow : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_snowflake_cold
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_snow)
    }

    object Daylight : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_horizon_alt
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_daylight)
    }

    object Sunrise : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_sunrise
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_sunrise)
    }

    object Sunset : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_sunset
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_sunset)
    }

    object Theme : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_dark_mode_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_theme)
    }

    object TimeFormat : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_watch_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_time_format)
    }

    object Today : NBIconModel.FilledAndOutlined {
        override val resIdFilled: Int
            get() = R.drawable.ic_baseline_today_24
        override val resIdOutlined: Int
            get() = R.drawable.ic_outline_today_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_today)
    }

    object Units : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_thermostat_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_units)
    }

    object UVIndex : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_light_mode_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_uv_index)
    }

    object Visibility : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_visibility_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_visibility)
    }

    object Warning : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_warning_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_warning)
    }

    object WeatherDayBrokenClouds : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_cloudy
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_day_broken_clouds)
    }

    object WeatherDayClearSky : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_sunny
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_day_clear_sky)
    }

    object WeatherDayFewClouds : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_cloudy
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_day_few_clouds)
    }

    object WeatherDayMist : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_fog
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_day_mist)
    }

    object WeatherDayRain : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_rain
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_day_rain)
    }

    object WeatherDayScatteredClouds : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_cloud
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_day_scattered_clouds)
    }

    object WeatherDayShowerRain : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_showers
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_day_shower_rain)
    }

    object WeatherDaySnow : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_snow
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_day_snow)
    }

    object WeatherDayThunderstorm : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_lightning
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_day_thunderstorm)
    }

    object WeatherNightBrokenClouds : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_cloudy
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_night_broken_clouds)
    }

    object WeatherNightClearSky : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_clear
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_night_clear_sky)
    }

    object WeatherNightFewClouds : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_alt_cloudy
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_night_few_clouds)
    }

    object WeatherNightMist : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_fog
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_night_mist)
    }

    object WeatherNightRain : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_alt_rain
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_night_rain)
    }

    object WeatherNightScatteredClouds : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_cloud
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_night_scattered_clouds)
    }

    object WeatherNightShowerRain : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_alt_showers
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_night_shower_rain)
    }

    object WeatherNightSnow : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_alt_snow
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_night_snow)
    }

    object WeatherNightThunderstorm : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_alt_lightning
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_weather_night_thunderstorm)
    }

    object Website : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_link_24
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_website)
    }

    object WindDegrees : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_wind_deg
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_wind_degrees)
    }

    object WindGust : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_strong_wind
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_wind_gust)
    }

    object WindSpeed : NBIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_windy
        override val contentDescription: NBString
            get() = NBString.Resource(R.string.icon_content_description_wind_speed)
    }

}