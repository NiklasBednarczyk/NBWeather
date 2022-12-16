package de.niklasbednarczyk.nbweather.feature.location.cards.models.overview

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.models.NationalWeatherAlertModelData

data class LocationCardOverviewAlertModel(
    val text: NBString?,
    val moreAlerts: NBString?
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
                    val moreAlerts = NBString.Resource(
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