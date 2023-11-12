package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.data.onecall.models.HourlyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.CloudinessForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.DewPointForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.FeelsLikeForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.HumidityForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ProbabilityOfPrecipitationForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.RainForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.SnowForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.UVIndexForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.VisibilityForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindGustForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindSpeedForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.sortOrder

data class ForecastHourlyViewData(
    val axes: List<NBDateTimeDisplayModel>,
    val graphs: List<List<ForecastValue.Units>>
) {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): ForecastHourlyViewData? {
            return from(
                timezoneOffset = oneCall.timezoneOffset,
                hourlyForecasts = oneCall.hourlyForecasts
            )
        }

        fun from(
            timezoneOffset: NBTimezoneOffsetValue?,
            hourlyForecasts: List<HourlyForecastModelData>,
        ): ForecastHourlyViewData? {
            if (hourlyForecasts.isEmpty()) return null

            val axes = hourlyForecasts.mapNotNull { hourlyForecast ->
                NBDateTimeDisplayModel.from(hourlyForecast.forecastTime, timezoneOffset)
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
            ).filter { graph ->
                val firstElement = graph.firstOrNull() ?: return@filter false

                val sameSizeAsAxis = graph.size == axes.size
                val valuesNotAllZeros = graph.any { value ->
                    value.unitsValue.value.toDouble() != 0.0
                }
                val allOfSameType =
                    graph.filterIsInstance(firstElement::class.java).size == graph.size

                sameSizeAsAxis && valuesNotAllZeros && allOfSameType
            }.sortedBy { unitsItem -> unitsItem.firstOrNull()?.sortOrder }

            return nbNullSafeList(axes, graphs) { a, g ->
                ForecastHourlyViewData(
                    axes = a,
                    graphs = g
                )
            }
        }
    }


}