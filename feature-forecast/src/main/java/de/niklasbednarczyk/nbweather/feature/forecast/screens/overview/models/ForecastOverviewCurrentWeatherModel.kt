package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.ForecastUnitsItem
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.sortOrder

data class ForecastOverviewCurrentWeatherModel(
    val items: List<ForecastUnitsItem>
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
                currentWeather.rain1hVolume,
                currentWeather.snow1hVolume,
                today?.probabilityOfPrecipitation
            ).sortedBy { item -> item.sortOrder }

            return nbNullSafeList(items) { i ->
                ForecastOverviewCurrentWeatherModel(
                    items = i
                )
            }
        }

        fun List<ForecastUnitsItem>.toRowItemsWithPlaceholders(rowItemCount: Int): List<ForecastUnitsItem?> {
            val rowItemsWithPlaceholders = toMutableList<ForecastUnitsItem?>()
            val remainingNeededItems = rowItemCount - size
            repeat(remainingNeededItems) {
                rowItemsWithPlaceholders.add(null)
            }
            return rowItemsWithPlaceholders
        }

    }

}