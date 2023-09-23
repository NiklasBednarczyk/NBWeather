package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.graphs.NBGraphsAxisModel
import de.niklasbednarczyk.nbweather.core.ui.graphs.NBGraphsViewData
import de.niklasbednarczyk.nbweather.core.ui.limit.NBLimitsItem
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.CloudinessUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.DewPointUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.FeelsLikeUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.ForecastUnitsItem
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.HumidityUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.PressureUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.ProbabilityOfPrecipitationUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.RainUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.SnowUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.TemperatureUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.UVIndexUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.VisibilityUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.WindGustUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.WindSpeedUnitsValue.Companion.orZero
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.limits
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.name
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.sortOrder

data class ForecastHourlyViewData(
    override val axes: List<NBGraphsAxisModel>, override val graphs: List<List<ForecastUnitsItem>>
) : NBGraphsViewData<ForecastUnitsItem> {

    override fun getAbsoluteValue(value: ForecastUnitsItem): Double {
        return value.unitsValue.value.toDouble()
    }

    override fun getName(value: ForecastUnitsItem): NBString {
        return value.name
    }

    override fun getLimits(value: ForecastUnitsItem): NBLimitsItem {
        return value.limits
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

            val temperatureGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.temperature.orZero() }

            val feelsLikeGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.feelsLikeTemperature.orZero() }

            val pressureGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.pressure.orZero() }

            val humidityGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.humidity.orZero() }

            val dewPointGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.dewPointTemperature.orZero() }

            val uvIndexGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.uvIndex.orZero() }

            val cloudinessGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.cloudiness.orZero() }

            val visibilityGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.visibility.orZero() }

            val windSpeedGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.windSpeed.orZero() }

            val windGustGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.windGust.orZero() }

            val probabilityOfPrecipitationGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.probabilityOfPrecipitation.orZero() }

            val rainGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.rain1hVolume.orZero() }

            val snowGraph =
                hourlyForecasts.map { hourlyForecast -> hourlyForecast.snow1hVolume.orZero() }

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
            ).sortedBy { unitsItem -> unitsItem.firstOrNull()?.sortOrder }

            return ForecastHourlyViewData(
                axes = axes,
                graphs = graphs
            )
        }

    }

}