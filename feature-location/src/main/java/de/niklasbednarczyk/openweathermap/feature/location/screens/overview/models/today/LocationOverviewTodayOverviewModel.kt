package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.overview.LocationOverviewTodayOverviewWeatherModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.overview.LocationOverviewTodayOverviewPrecipitationModel

data class LocationOverviewTodayOverviewModel(
    val weather: LocationOverviewTodayOverviewWeatherModel,
    val precipitation: LocationOverviewTodayOverviewPrecipitationModel
) : LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType
        ): LocationOverviewTodayOverviewModel? {
            val currentWeather = oneCall.currentWeather
            val minutelyForecasts = oneCall.minutelyForecasts
            val units = oneCall.metadata.units
            val timezoneOffset = oneCall.metadata.timezoneOffset

            val weather = LocationOverviewTodayOverviewWeatherModel.from(
                currentWeather,
                units
            )
            val precipitation = LocationOverviewTodayOverviewPrecipitationModel.from(
                minutelyForecasts = minutelyForecasts,
                timeFormat = timeFormat,
                timezoneOffset = timezoneOffset
            )

            return owmNullSafe(weather) { w ->
                LocationOverviewTodayOverviewModel(
                    weather = w,
                    precipitation = precipitation
                )
            }
        }

    }

}
