package de.niklasbednarczyk.openweathermap.feature.location.cards.models.overview

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.data.onecall.models.NationalWeatherAlertModelData

data class LocationCardOverviewAlertModel(
    val text: OwmString?,
    val moreAlerts: OwmString?
) {

    companion object {

        fun from(
            alerts: List<NationalWeatherAlertModelData>
        ): LocationCardOverviewAlertModel? {
            val size = alerts.size
            val text = alerts.firstOrNull()?.eventName ?: return null
            return when (size) {
                0 -> null
                1 -> LocationCardOverviewAlertModel(
                    text = text,
                    moreAlerts = null,
                )
                else -> {
                    val moreAlertsNo = size - 1
                    val moreAlerts = OwmString.Resource(
                        R.string.format_plus_prefix,
                        moreAlertsNo
                    )
                    LocationCardOverviewAlertModel(
                        text = text,
                        moreAlerts = moreAlerts
                    )
                }
            }
        }

    }

}