package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.header.LocationOverviewTodayHeaderWeatherModel

data class LocationOverviewTodayHeaderModel(
    val weather: LocationOverviewTodayHeaderWeatherModel
) : LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType
        ): LocationOverviewTodayHeaderModel? {
            val currentWeather = oneCall.currentWeather
            val minutelyForecasts = oneCall.minutelyForecasts
            val units = oneCall.metadata.units

            val weather = LocationOverviewTodayHeaderWeatherModel.from(
                currentWeather,
                units
            )

            return owmNullSafe(weather) { w ->
                LocationOverviewTodayHeaderModel(
                    weather = w
                )
            }
        }

    }

}
