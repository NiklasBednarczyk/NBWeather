package de.niklasbednarczyk.openweathermap.core.ui.icons

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R

object OwmIcons {

    object Back : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_arrow_back_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_back)
    }

    object Cancel : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_close_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_cancel)
    }

    object Cloudiness : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_cloud_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_cloudiness)
    }

    object ColorScheme : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_palette_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_color_scheme)
    }

    object Daily : OwmIconModel.FilledAndOutlined {
        override val resIdFilled: Int
            get() = R.drawable.ic_baseline_date_range_24
        override val resIdOutlined: Int
            get() = R.drawable.ic_outline_date_range_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_daily)
    }

    object Delete : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_delete_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_delete)
    }

    object DewPoint : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_thermometer
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_dew_point)
    }

    object Drawer : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_menu_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_drawer)
    }

    object ErrorNoInternet : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_cloud_off_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_error_no_internet)
    }

    object ErrorUnknown : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_error_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_error_unknown)
    }

    object Expand : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_expand_more_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_expand)
    }

    object FindLocation : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_my_location_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_find_location)
    }

    object Hourly : OwmIconModel.FilledAndOutlined {
        override val resIdFilled: Int
            get() = R.drawable.ic_baseline_watch_later_24
        override val resIdOutlined: Int
            get() = R.drawable.ic_outline_watch_later_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_hourly)
    }

    object Humidity : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_humidity
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_humidity)
    }

    object Language : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_translate_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_language)
    }

    object Location : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_place_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_location)
    }

    object Pressure : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_barometer
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_pressure)
    }

    object ProbabilityOfPrecipitation : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_umbrella
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_probability_of_precipitation)
    }

    object Rain : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_raindrop
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_rain)
    }

    object Search : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_search_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_search)
    }

    object Settings : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_settings_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_settings)
    }

    object Snow : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_snowflake_cold
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_snow)
    }

    object Theme : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_dark_mode_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_theme)
    }

    object TimeFormat : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_watch_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_time_format)
    }

    object Today : OwmIconModel.FilledAndOutlined {
        override val resIdFilled: Int
            get() = R.drawable.ic_baseline_today_24
        override val resIdOutlined: Int
            get() = R.drawable.ic_outline_today_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_today)
    }

    object Units : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_thermostat_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_units)
    }

    object UVIndex : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_light_mode_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_uv_index)
    }

    object Visibility : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_visibility_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_visibility)
    }

    object Warning : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_warning_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_warning)
    }

    object WeatherDayBrokenClouds : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_cloudy
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_day_broken_clouds)
    }

    object WeatherDayClearSky : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_sunny
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_day_clear_sky)
    }

    object WeatherDayFewClouds : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_cloudy
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_day_few_clouds)
    }

    object WeatherDayMist : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_fog
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_day_mist)
    }

    object WeatherDayRain : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_rain
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_day_rain)
    }

    object WeatherDayScatteredClouds : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_cloud
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_day_scattered_clouds)
    }

    object WeatherDayShowerRain : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_showers
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_day_shower_rain)
    }

    object WeatherDaySnow : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_snow
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_day_snow)
    }

    object WeatherDayThunderstorm : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_day_lightning
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_day_thunderstorm)
    }

    object WeatherNightBrokenClouds : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_cloudy
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_night_broken_clouds)
    }

    object WeatherNightClearSky : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_clear
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_night_clear_sky)
    }

    object WeatherNightFewClouds : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_alt_cloudy
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_night_few_clouds)
    }

    object WeatherNightMist : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_fog
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_night_mist)
    }

    object WeatherNightRain : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_alt_rain
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_night_rain)
    }

    object WeatherNightScatteredClouds : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_cloud
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_night_scattered_clouds)
    }

    object WeatherNightShowerRain : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_alt_showers
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_night_shower_rain)
    }

    object WeatherNightSnow : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_alt_snow
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_night_snow)
    }

    object WeatherNightThunderstorm : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_night_alt_lightning
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_weather_night_thunderstorm)
    }

    object WindDegrees : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_wind_deg
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_wind_degrees)
    }

    object WindDirection : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.ic_baseline_navigation_24
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_wind_direction)
    }

    object WindGust : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_strong_wind
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_wind_gust)
    }

    object WindSpeed : OwmIconModel.Default {
        override val resId: Int
            get() = R.drawable.wi_windy
        override val contentDescription: OwmString
            get() = OwmString.Resource(R.string.icon_content_description_wind_speed)
    }

}