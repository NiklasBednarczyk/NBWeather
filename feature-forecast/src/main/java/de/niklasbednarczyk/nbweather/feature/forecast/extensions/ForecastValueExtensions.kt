package de.niklasbednarczyk.nbweather.feature.forecast.extensions

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.common.displayValueWithSymbol
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridIconModel
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.CloudinessForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.DewPointForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.FeelsLikeForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.HumidityForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ProbabilityOfPrecipitationForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.RainForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.SnowForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.UVIndexForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.VisibilityForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindDegreesForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindGustForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindSpeedForecastValue
import de.niklasbednarczyk.nbweather.feature.forecast.constants.ForecastUnitsLimits
import de.niklasbednarczyk.nbweather.feature.forecast.models.ForecastUnitsLimitsItem

val ForecastValue.displayValue: NBString?
    @Composable
    get() = when (this) {
        is ForecastValue.Units -> unitsValue.displayValueWithSymbol
        is WindDegreesForecastValue -> type?.displayText
    }

val ForecastValue.icon: NBIconModel
    get() = when (this) {
        is CloudinessForecastValue -> NBIcons.Cloudiness
        is DewPointForecastValue -> NBIcons.DewPoint
        is FeelsLikeForecastValue -> NBIcons.FeelsLike
        is HumidityForecastValue -> NBIcons.Humidity
        is PressureForecastValue -> NBIcons.Pressure
        is ProbabilityOfPrecipitationForecastValue -> NBIcons.ProbabilityOfPrecipitation
        is RainForecastValue -> NBIcons.Rain
        is SnowForecastValue -> NBIcons.Snow
        is TemperatureForecastValue -> NBIcons.Temperature
        is UVIndexForecastValue -> NBIcons.UVIndex
        is VisibilityForecastValue -> NBIcons.Visibility
        is WindDegreesForecastValue -> NBIcons.WindDirection
        is WindGustForecastValue -> NBIcons.WindGust
        is WindSpeedForecastValue -> NBIcons.WindSpeed
    }

val ForecastValue.name: NBString
    get() {
        val resId = when (this) {
            is CloudinessForecastValue -> R.string.screen_forecast_common_forecast_name_cloudiness
            is DewPointForecastValue -> R.string.screen_forecast_common_forecast_name_dew_point
            is FeelsLikeForecastValue -> R.string.screen_forecast_common_forecast_name_feels_like
            is HumidityForecastValue -> R.string.screen_forecast_common_forecast_name_humidity
            is PressureForecastValue -> R.string.screen_forecast_common_forecast_name_pressure
            is ProbabilityOfPrecipitationForecastValue -> R.string.screen_forecast_common_forecast_name_probability_of_precipitation
            is RainForecastValue -> R.string.screen_forecast_common_forecast_name_rain
            is SnowForecastValue -> R.string.screen_forecast_common_forecast_name_snow
            is TemperatureForecastValue -> R.string.screen_forecast_common_forecast_name_temperature
            is UVIndexForecastValue -> R.string.screen_forecast_common_forecast_name_uv_index
            is VisibilityForecastValue -> R.string.screen_forecast_common_forecast_name_visibility
            is WindDegreesForecastValue -> R.string.screen_forecast_common_forecast_name_wind_direction
            is WindGustForecastValue -> R.string.screen_forecast_common_forecast_name_wind_gust
            is WindSpeedForecastValue -> R.string.screen_forecast_common_forecast_name_wind_speed
        }
        return NBString.ResString(resId)
    }

val ForecastValue.sortOrder: Int
    get() = when (this) {
        is TemperatureForecastValue -> 1
        is FeelsLikeForecastValue -> 2
        is ProbabilityOfPrecipitationForecastValue -> 3
        is RainForecastValue -> 4
        is SnowForecastValue -> 5
        is WindSpeedForecastValue -> 6
        is WindGustForecastValue -> 7
        is WindDegreesForecastValue -> 8
        is PressureForecastValue -> 9
        is HumidityForecastValue -> 10
        is DewPointForecastValue -> 11
        is CloudinessForecastValue -> 12
        is UVIndexForecastValue -> 13
        is VisibilityForecastValue -> 14
    }


val ForecastValue.Units.limits: ForecastUnitsLimitsItem
    get() = when (this) {
        is CloudinessForecastValue,
        is HumidityForecastValue -> ForecastUnitsLimits.Percent

        is DewPointForecastValue,
        is FeelsLikeForecastValue,
        is TemperatureForecastValue -> ForecastUnitsLimits.Temperature

        is PressureForecastValue -> ForecastUnitsLimits.Pressure

        is ProbabilityOfPrecipitationForecastValue -> ForecastUnitsLimits.Probability

        is RainForecastValue,
        is SnowForecastValue -> ForecastUnitsLimits.Precipitation

        is UVIndexForecastValue -> ForecastUnitsLimits.UVIndex

        is VisibilityForecastValue -> ForecastUnitsLimits.Distance

        is WindGustForecastValue,
        is WindSpeedForecastValue -> ForecastUnitsLimits.WindSpeed
    }

@Composable
fun List<ForecastValue>.toGridItems(): List<NBGridModel> {
    return sortedBy { forecastValue ->
        forecastValue.sortOrder
    }.map { forecastValue ->
        val rotationDegrees = if (forecastValue is WindDegreesForecastValue) {
            forecastValue.rotationDegrees
        } else {
            0f
        }

        NBGridModel(
            name = forecastValue.name,
            icon = NBGridIconModel(
                icon = forecastValue.icon,
                rotationDegrees = rotationDegrees
            ),
            value = forecastValue.displayValue
        )
    }
}