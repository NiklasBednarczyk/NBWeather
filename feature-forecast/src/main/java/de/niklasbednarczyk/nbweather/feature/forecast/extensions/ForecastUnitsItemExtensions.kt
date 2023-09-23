package de.niklasbednarczyk.nbweather.feature.forecast.extensions

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.limit.NBLimitsItem
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.CloudinessUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.DewPointUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.FeelsLikeUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.HumidityUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.ForecastUnitsItem
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.PressureUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.ProbabilityOfPrecipitationUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.RainUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.SnowUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.TemperatureUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.UVIndexUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.VisibilityUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.WindGustUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.WindSpeedUnitsValue
import de.niklasbednarczyk.nbweather.feature.forecast.constants.ConstantsFeatureForecast

val ForecastUnitsItem.icon: NBIconModel
    get() = when (this) {
        is CloudinessUnitsValue -> NBIcons.Cloudiness
        is DewPointUnitsValue -> NBIcons.DewPoint
        is FeelsLikeUnitsValue -> NBIcons.FeelsLike
        is HumidityUnitsValue -> NBIcons.Humidity
        is PressureUnitsValue -> NBIcons.Pressure
        is ProbabilityOfPrecipitationUnitsValue -> NBIcons.ProbabilityOfPrecipitation
        is RainUnitsValue -> NBIcons.Rain
        is SnowUnitsValue -> NBIcons.Snow
        is TemperatureUnitsValue -> NBIcons.Temperature
        is UVIndexUnitsValue -> NBIcons.UVIndex
        is VisibilityUnitsValue -> NBIcons.Visibility
        is WindGustUnitsValue -> NBIcons.WindGust
        is WindSpeedUnitsValue -> NBIcons.WindSpeed
    }

val ForecastUnitsItem.limits: NBLimitsItem
    get() = when (this) {
        is CloudinessUnitsValue,
        is HumidityUnitsValue -> ConstantsFeatureForecast.Limits.Percent

        is DewPointUnitsValue,
        is FeelsLikeUnitsValue,
        is TemperatureUnitsValue -> ConstantsFeatureForecast.Limits.Temperature

        is PressureUnitsValue -> ConstantsFeatureForecast.Limits.Pressure

        is ProbabilityOfPrecipitationUnitsValue -> ConstantsFeatureForecast.Limits.Probability

        is RainUnitsValue,
        is SnowUnitsValue -> ConstantsFeatureForecast.Limits.Precipitation

        is UVIndexUnitsValue -> ConstantsFeatureForecast.Limits.UVIndex

        is VisibilityUnitsValue -> ConstantsFeatureForecast.Limits.Distance

        is WindGustUnitsValue,
        is WindSpeedUnitsValue -> ConstantsFeatureForecast.Limits.WindSpeed
    }

val ForecastUnitsItem.name: NBString
    get() {
        val resId = when (this) {
            is CloudinessUnitsValue -> R.string.screen_forecast_common_forecast_units_name_cloudiness
            is DewPointUnitsValue -> R.string.screen_forecast_common_forecast_units_name_dew_point
            is FeelsLikeUnitsValue -> R.string.screen_forecast_common_forecast_units_name_feels_like
            is HumidityUnitsValue -> R.string.screen_forecast_common_forecast_units_name_humidity
            is PressureUnitsValue -> R.string.screen_forecast_common_forecast_units_name_pressure
            is ProbabilityOfPrecipitationUnitsValue -> R.string.screen_forecast_common_forecast_units_name_probability_of_precipitation
            is RainUnitsValue -> R.string.screen_forecast_common_forecast_units_name_rain
            is SnowUnitsValue -> R.string.screen_forecast_common_forecast_units_name_snow
            is TemperatureUnitsValue -> R.string.screen_forecast_common_forecast_units_name_temperature
            is UVIndexUnitsValue -> R.string.screen_forecast_common_forecast_units_name_uv_index
            is VisibilityUnitsValue -> R.string.screen_forecast_common_forecast_units_name_visibility
            is WindGustUnitsValue -> R.string.screen_forecast_common_forecast_units_name_wind_gust
            is WindSpeedUnitsValue -> R.string.screen_forecast_common_forecast_units_name_wind_speed
        }
        return NBString.ResString(resId)
    }

val ForecastUnitsItem.sortOrder: Int
    get() = when (this) {
        is TemperatureUnitsValue -> 1
        is FeelsLikeUnitsValue -> 2
        is ProbabilityOfPrecipitationUnitsValue -> 3
        is RainUnitsValue -> 4
        is SnowUnitsValue -> 5
        is WindSpeedUnitsValue -> 6
        is WindGustUnitsValue -> 7
        is PressureUnitsValue -> 8
        is HumidityUnitsValue -> 9
        is DewPointUnitsValue -> 10
        is CloudinessUnitsValue -> 11
        is UVIndexUnitsValue -> 12
        is VisibilityUnitsValue -> 13
    }