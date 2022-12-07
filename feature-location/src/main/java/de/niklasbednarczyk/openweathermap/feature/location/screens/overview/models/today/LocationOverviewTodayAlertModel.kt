package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.data.onecall.models.NationalWeatherAlertModelData
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

data class LocationOverviewTodayAlertModel(
    val text: OwmString?,
    val moreAlerts: OwmString?
) : LocationOverviewTodayItem {

    companion object {

        fun from(oneCall: OneCallModelData): LocationOverviewTodayAlertModel? {
            val alerts = oneCall.nationalWeatherAlerts
            val size = alerts.size
            val text = alerts.firstOrNull()?.eventName ?: return null
            return when (size) {
                0 -> null
                1 -> LocationOverviewTodayAlertModel(text, null)
                else -> {
                    val moreAlertsNo = size - 1
                    val moreAlerts = OwmString.Resource(
                        R.string.format_plus_prefix,
                        moreAlertsNo
                    )
                    LocationOverviewTodayAlertModel(text, moreAlerts)
                }
            }
        }

    }

}