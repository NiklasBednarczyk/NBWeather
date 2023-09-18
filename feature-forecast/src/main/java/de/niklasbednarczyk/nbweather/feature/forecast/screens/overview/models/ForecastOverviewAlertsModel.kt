package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

data class ForecastOverviewAlertsModel(
    val text: NBString?,
    val otherAlertsText: NBString?
) : ForecastOverviewItem {

    override val title = null

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): ForecastOverviewAlertsModel? {
            return nbNullSafeList(oneCall.nationalWeatherAlerts) { alerts ->
                val text = alerts.firstOrNull()?.eventName
                    ?: NBString.Resource(R.string.screen_forecast_overview_alerts_text_fallback)
                val otherAlertsText = when (val size = alerts.size) {
                    0 -> return null
                    1 -> null
                    else -> NBString.Resource(
                        R.string.format_plus_prefix,
                        size - 1
                    )
                }
                ForecastOverviewAlertsModel(
                    text = text,
                    otherAlertsText = otherAlertsText
                )
            }
        }

    }

}