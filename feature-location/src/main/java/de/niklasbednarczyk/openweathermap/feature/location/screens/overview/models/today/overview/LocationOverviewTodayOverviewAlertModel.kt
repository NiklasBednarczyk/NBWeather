package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.overview

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.data.onecall.models.NationalWeatherAlertModelData
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayItem

data class LocationOverviewTodayOverviewAlertModel(
    val text: OwmString?,
    val moreAlerts: OwmString?
) {

    companion object {

        fun from(
            alerts: List<NationalWeatherAlertModelData>
        ): LocationOverviewTodayOverviewAlertModel? {
            val size = alerts.size
            val text = alerts.firstOrNull()?.eventName ?: return null
            return when (size) {
                0 -> null
                1 -> LocationOverviewTodayOverviewAlertModel(
                    text = text,
                    moreAlerts = null,
                )
                else -> {
                    val moreAlertsNo = size - 1
                    val moreAlerts = OwmString.Resource(
                        R.string.format_plus_prefix,
                        moreAlertsNo
                    )
                    LocationOverviewTodayOverviewAlertModel(
                        text = text,
                        moreAlerts = moreAlerts
                    )
                }
            }
        }

    }

}