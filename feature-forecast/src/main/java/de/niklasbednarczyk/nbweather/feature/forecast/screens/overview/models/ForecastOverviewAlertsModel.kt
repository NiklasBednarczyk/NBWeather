package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.models.NationalWeatherAlertModelData

data class ForecastOverviewAlertsModel(
    val eventName: NBString,
    val otherAlerts: NBString?
) : ForecastOverviewItem {

    companion object {

        fun from(
            nationalWeatherAlerts: List<NationalWeatherAlertModelData>
        ): ForecastOverviewAlertsModel? {
            return nbNullSafeList(nationalWeatherAlerts) { alerts ->
                val eventName = alerts.firstOrNull()?.eventName
                    ?: NBString.ResString(R.string.screen_forecast_overview_alerts_event_name_fallback)
                val otherAlerts = when (val size = alerts.size) {
                    0 -> return null
                    1 -> null
                    else -> NBString.ResString(
                        R.string.format_plus_prefix,
                        size - 1
                    )
                }
                ForecastOverviewAlertsModel(
                    eventName = eventName,
                    otherAlerts = otherAlerts
                )
            }
        }

    }

}