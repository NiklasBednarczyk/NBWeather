package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.overview.LocationOverviewTodayOverviewAlertModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.overview.LocationOverviewTodayOverviewPrecipitationModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.overview.LocationOverviewTodayOverviewWeatherModel

data class LocationOverviewTodayOverviewModel(
    override val cardTitle: OwmString?,
    val alert: LocationOverviewTodayOverviewAlertModel?,
    val weather: LocationOverviewTodayOverviewWeatherModel,
    val precipitation: LocationOverviewTodayOverviewPrecipitationModel
) : LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType
        ): LocationOverviewTodayOverviewModel? {
            val cardTitle =
                OwmString.Resource(R.string.screen_location_overview_today_card_overview_title)

            val alerts = oneCall.nationalWeatherAlerts
            val currentWeather = oneCall.currentWeather
            val minutelyForecasts = oneCall.minutelyForecasts
            val units = oneCall.metadata.units
            val timezoneOffset = oneCall.metadata.timezoneOffset

            val alert = LocationOverviewTodayOverviewAlertModel.from(
                alerts = alerts
            )

            val weather = LocationOverviewTodayOverviewWeatherModel.from(
                currentWeather = currentWeather,
                units = units
            )
            val precipitation = LocationOverviewTodayOverviewPrecipitationModel.from(
                minutelyForecasts = minutelyForecasts,
                timeFormat = timeFormat,
                timezoneOffset = timezoneOffset
            )

            return owmNullSafe(weather) { w ->
                LocationOverviewTodayOverviewModel(
                    cardTitle = cardTitle,
                    alert = alert,
                    weather = w,
                    precipitation = precipitation
                )
            }
        }

    }

}
