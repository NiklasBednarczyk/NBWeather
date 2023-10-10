package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
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
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.sortOrder

data class ForecastHourlyViewData(
    val axes: List<ForecastHourlyAxisModel>,
    val graphs: List<List<ForecastValue.Units>>
) {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): ForecastHourlyViewData? {

            val timezoneOffset = oneCall.timezoneOffset
            val hourlyForecasts = oneCall.hourlyForecasts

            if (hourlyForecasts.isEmpty()) return null

            val axes = hourlyForecasts.map { hourlyForecast ->
                val forecastTime =
                    NBDateTimeDisplayModel.from(hourlyForecast.forecastTime, timezoneOffset)
                val icon = hourlyForecast.weather?.icon?.icon

                ForecastHourlyAxisModel(
                    forecastTime = forecastTime ?: return null,
                    icon = icon ?: return null
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