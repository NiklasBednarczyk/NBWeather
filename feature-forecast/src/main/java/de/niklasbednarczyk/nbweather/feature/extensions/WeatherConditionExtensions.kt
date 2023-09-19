package de.niklasbednarczyk.nbweather.feature.extensions

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherConditionType

val WeatherConditionType.displayText: NBString
    get() {
        val resId = when (this) {
            WeatherConditionType.THUNDERSTORM_WITH_LIGHT_RAIN -> R.string.screen_forecast_common_weather_condition_thunderstorm_with_light_rain
            WeatherConditionType.THUNDERSTORM_WITH_RAIN -> R.string.screen_forecast_common_weather_condition_thunderstorm_with_rain
            WeatherConditionType.THUNDERSTORM_WITH_HEAVY_RAIN -> R.string.screen_forecast_common_weather_condition_thunderstorm_with_heavy_rain
            WeatherConditionType.LIGHT_THUNDERSTORM -> R.string.screen_forecast_common_weather_condition_light_thunderstorm
            WeatherConditionType.THUNDERSTORM -> R.string.screen_forecast_common_weather_condition_thunderstorm
            WeatherConditionType.HEAVY_THUNDERSTORM -> R.string.screen_forecast_common_weather_condition_heavy_thunderstorm
            WeatherConditionType.RAGGED_THUNDERSTORM -> R.string.screen_forecast_common_weather_condition_ragged_thunderstorm
            WeatherConditionType.THUNDERSTORM_WITH_LIGHT_DRIZZLE -> R.string.screen_forecast_common_weather_condition_thunderstorm_with_light_drizzle
            WeatherConditionType.THUNDERSTORM_WITH_DRIZZLE -> R.string.screen_forecast_common_weather_condition_thunderstorm_with_drizzle
            WeatherConditionType.THUNDERSTORM_WITH_HEAVY_DRIZZLE -> R.string.screen_forecast_common_weather_condition_thunderstorm_with_heavy_drizzle
            WeatherConditionType.LIGHT_INTENSITY_DRIZZLE -> R.string.screen_forecast_common_weather_condition_light_intensity_drizzle
            WeatherConditionType.DRIZZLE -> R.string.screen_forecast_common_weather_condition_drizzle
            WeatherConditionType.HEAVY_INTENSITY_DRIZZLE -> R.string.screen_forecast_common_weather_condition_heavy_intensity_drizzle
            WeatherConditionType.LIGHT_INTENSITY_DRIZZLE_RAIN -> R.string.screen_forecast_common_weather_condition_light_intensity_drizzle_rain
            WeatherConditionType.DRIZZLE_RAIN -> R.string.screen_forecast_common_weather_condition_drizzle_rain
            WeatherConditionType.HEAVY_INTENSITY_DRIZZLE_RAIN -> R.string.screen_forecast_common_weather_condition_heavy_intensity_drizzle_rain
            WeatherConditionType.SHOWER_RAIN_AND_DRIZZLE -> R.string.screen_forecast_common_weather_condition_shower_rain_and_drizzle
            WeatherConditionType.HEAVY_SHOWER_RAIN_AND_DRIZZLE -> R.string.screen_forecast_common_weather_condition_heavy_shower_rain_and_drizzle
            WeatherConditionType.SHOWER_DRIZZLE -> R.string.screen_forecast_common_weather_condition_shower_drizzle
            WeatherConditionType.LIGHT_RAIN -> R.string.screen_forecast_common_weather_condition_light_rain
            WeatherConditionType.MODERATE_RAIN -> R.string.screen_forecast_common_weather_condition_moderate_rain
            WeatherConditionType.HEAVY_INTENSITY_RAIN -> R.string.screen_forecast_common_weather_condition_heavy_intensity_rain
            WeatherConditionType.VERY_HEAVY_RAIN -> R.string.screen_forecast_common_weather_condition_very_heavy_rain
            WeatherConditionType.EXTREME_RAIN -> R.string.screen_forecast_common_weather_condition_extreme_rain
            WeatherConditionType.FREEZING_RAIN -> R.string.screen_forecast_common_weather_condition_freezing_rain
            WeatherConditionType.LIGHT_INTENSITY_SHOWER_RAIN -> R.string.screen_forecast_common_weather_condition_light_intensity_shower_rain
            WeatherConditionType.SHOWER_RAIN -> R.string.screen_forecast_common_weather_condition_shower_rain
            WeatherConditionType.HEAVY_INTENSITY_SHOWER_RAIN -> R.string.screen_forecast_common_weather_condition_heavy_intensity_shower_rain
            WeatherConditionType.RAGGED_SHOWER_RAIN -> R.string.screen_forecast_common_weather_condition_ragged_shower_rain
            WeatherConditionType.LIGHT_SNOW -> R.string.screen_forecast_common_weather_condition_light_snow
            WeatherConditionType.SNOW -> R.string.screen_forecast_common_weather_condition_snow
            WeatherConditionType.HEAVY_SNOW -> R.string.screen_forecast_common_weather_condition_heavy_snow
            WeatherConditionType.SLEET -> R.string.screen_forecast_common_weather_condition_sleet
            WeatherConditionType.LIGHT_SHOWER_SLEET -> R.string.screen_forecast_common_weather_condition_light_shower_sleet
            WeatherConditionType.SHOWER_SLEET -> R.string.screen_forecast_common_weather_condition_shower_sleet
            WeatherConditionType.LIGHT_RAIN_AND_SNOW -> R.string.screen_forecast_common_weather_condition_light_rain_and_snow
            WeatherConditionType.RAIN_AND_SNOW -> R.string.screen_forecast_common_weather_condition_rain_and_snow
            WeatherConditionType.LIGHT_SHOWER_SNOW -> R.string.screen_forecast_common_weather_condition_light_shower_snow
            WeatherConditionType.SHOWER_SNOW -> R.string.screen_forecast_common_weather_condition_shower_snow
            WeatherConditionType.HEAVY_SHOWER_SNOW -> R.string.screen_forecast_common_weather_condition_heavy_shower_snow
            WeatherConditionType.MIST -> R.string.screen_forecast_common_weather_condition_mist
            WeatherConditionType.SMOKE -> R.string.screen_forecast_common_weather_condition_smoke
            WeatherConditionType.HAZE -> R.string.screen_forecast_common_weather_condition_haze
            WeatherConditionType.SAND_DUST_WHIRLS -> R.string.screen_forecast_common_weather_condition_sand_dust_whirls
            WeatherConditionType.FOG -> R.string.screen_forecast_common_weather_condition_fog
            WeatherConditionType.SAND -> R.string.screen_forecast_common_weather_condition_sand
            WeatherConditionType.DUST -> R.string.screen_forecast_common_weather_condition_dust
            WeatherConditionType.VOLCANIC_ASH -> R.string.screen_forecast_common_weather_condition_volcanic_ash
            WeatherConditionType.SQUALLS -> R.string.screen_forecast_common_weather_condition_squalls
            WeatherConditionType.TORNADO -> R.string.screen_forecast_common_weather_condition_tornado
            WeatherConditionType.CLEAR_SKY -> R.string.screen_forecast_common_weather_condition_clear_sky
            WeatherConditionType.FEW_CLOUDS -> R.string.screen_forecast_common_weather_condition_few_clouds
            WeatherConditionType.SCATTERED_CLOUDS -> R.string.screen_forecast_common_weather_condition_scattered_clouds
            WeatherConditionType.BROKEN_CLOUDS -> R.string.screen_forecast_common_weather_condition_broken_clouds
            WeatherConditionType.OVERCAST_CLOUDS -> R.string.screen_forecast_common_weather_condition_overcast_clouds
        }
        return NBString.ResString(resId)
    }