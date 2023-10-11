package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ForecastValue

data class ForecastOverviewCurrentWeatherModel(
    val items: List<ForecastValue>
) : ForecastOverviewItem {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): ForecastOverviewCurrentWeatherModel? {
            val currentWeather = oneCall.currentWeather
            val today = oneCall.today

            val items = listOfNotNull(
                currentWeather.feelsLikeTemperature,
                currentWeather.pressure,
                currentWeather.humidity,
                currentWeather.dewPointTemperature,
                currentWeather.cloudiness,
                currentWeather.uvIndex,
                currentWeather.visibility,
                currentWeather.windSpeed,
                currentWeather.windGust,
                currentWeather.windDegrees,
                currentWeather.rain1hVolume,
                currentWeather.snow1hVolume,
                today?.probabilityOfPrecipitation
            )

            return nbNullSafeList(items) { i ->
                ForecastOverviewCurrentWeatherModel(
                    items = i
                )
            }
        }

    }

}