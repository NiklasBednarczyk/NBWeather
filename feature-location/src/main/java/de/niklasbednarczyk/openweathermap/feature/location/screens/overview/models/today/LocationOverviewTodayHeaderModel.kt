package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.header.LocationOverviewTodayHeaderWeatherModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.header.LocationOverviewTodayPrecipitationModel

data class LocationOverviewTodayHeaderModel(
    val weather: LocationOverviewTodayHeaderWeatherModel,
    val precipitation: LocationOverviewTodayPrecipitationModel
) : LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType
        ): LocationOverviewTodayHeaderModel? {
            val currentWeather = oneCall.currentWeather
            val minutelyForecasts = oneCall.minutelyForecasts
            val units = oneCall.metadata.units
            val timezoneOffset = oneCall.metadata.timezoneOffset

            val weather = LocationOverviewTodayHeaderWeatherModel.from(
                currentWeather,
                units
            )
            val precipitation = LocationOverviewTodayPrecipitationModel.from(
                minutelyForecasts = minutelyForecasts,
                timeFormat = timeFormat,
                timezoneOffset = timezoneOffset
            )

            return owmNullSafe(weather) { w ->
                LocationOverviewTodayHeaderModel(
                    weather = w,
                    precipitation = precipitation
                )
            }
        }

    }

}
