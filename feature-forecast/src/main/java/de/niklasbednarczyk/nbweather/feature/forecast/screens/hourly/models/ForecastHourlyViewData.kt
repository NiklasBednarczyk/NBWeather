package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.graphs.NBGraphModel
import de.niklasbednarczyk.nbweather.core.ui.graphs.NBGraphsAxisModel
import de.niklasbednarczyk.nbweather.core.ui.graphs.NBGraphsViewData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.units.DistanceValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PercentValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PressureValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.ProbabilityValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue.Long.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.UVIndexValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.WindSpeedValue.Companion.orZero
import de.niklasbednarczyk.nbweather.feature.forecast.constants.ConstantsFeatureForecast
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon

data class ForecastHourlyViewData(
    override val axes: List<NBGraphsAxisModel>,
    override val graphs: List<NBGraphModel<NBUnitsValue>>
) : NBGraphsViewData<NBUnitsValue> {

    override fun getAbsoluteValue(value: NBUnitsValue): Double {
        return value.value.toDouble()
    }

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): ForecastHourlyViewData? {
            val timezoneOffset = oneCall.timezoneOffset
            val hourlyForecasts = oneCall.hourlyForecasts

            if (hourlyForecasts.isEmpty()) return null

            val axes = hourlyForecasts.mapNotNull { hourlyForecast ->
                val forecastTime =
                    NBDateTimeDisplayModel.from(hourlyForecast.forecastTime, timezoneOffset)
                val icon = hourlyForecast.weather?.icon?.icon

                NBGraphsAxisModel(
                    forecastTime = forecastTime ?: return@mapNotNull null,
                    icon = icon ?: return@mapNotNull null
                )
            }

            val temperatureGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_temperature),
                limits = ConstantsFeatureForecast.Limits.Temperature,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.temperature?.getLong().orZero() }
            )

            val feelsLikeGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_feels_like),
                limits = ConstantsFeatureForecast.Limits.Temperature,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.feelsLikeTemperature?.getLong().orZero() }
            )

            val pressureGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_pressure),
                limits = ConstantsFeatureForecast.Limits.Pressure,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.pressure.orZero() }
            )

            val humidityGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_humidity),
                limits = ConstantsFeatureForecast.Limits.Percent,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.humidity.orZero() }
            )

            val dewPointGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_dew_point),
                limits = ConstantsFeatureForecast.Limits.Temperature,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.dewPointTemperature?.getLong().orZero() }
            )

            val uvIndexGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_uv_index),
                limits = ConstantsFeatureForecast.Limits.UVIndex,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.uvIndex.orZero() }
            )

            val cloudinessGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_cloudiness),
                limits = ConstantsFeatureForecast.Limits.Percent,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.cloudiness.orZero() }
            )

            val visibilityGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_visibility),
                limits = ConstantsFeatureForecast.Limits.Distance,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.visibility.orZero() }
            )

            val windSpeedGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_wind_speed),
                limits = ConstantsFeatureForecast.Limits.WindSpeed,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.windSpeed.orZero() }
            )

            val windGustGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_wind_gust),
                limits = ConstantsFeatureForecast.Limits.WindSpeed,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.windGust.orZero() }
            )

            val probabilityOfPrecipitationGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_probability_of_precipitation),
                limits = ConstantsFeatureForecast.Limits.Probability,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.probabilityOfPrecipitation.orZero() }
            )

            val rainGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_rain),
                limits = ConstantsFeatureForecast.Limits.Precipitation,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.rain1hVolume.orZero() }
            )

            val snowGraph = NBGraphModel<NBUnitsValue>(
                name = NBString.ResString(R.string.screen_forecast_common_name_snow),
                limits = ConstantsFeatureForecast.Limits.Precipitation,
                values = hourlyForecasts.map { hourlyForecast -> hourlyForecast.snow1hVolume.orZero() }
            )

            val graphs = listOf(
                temperatureGraph,
                feelsLikeGraph,
                pressureGraph,
                humidityGraph,
                dewPointGraph,
                uvIndexGraph,
                cloudinessGraph,
                visibilityGraph,
                windSpeedGraph,
                windGustGraph,
                probabilityOfPrecipitationGraph,
                rainGraph,
                snowGraph
            )

            return ForecastHourlyViewData(
                axes = axes,
                graphs = graphs
            )
        }

    }

}