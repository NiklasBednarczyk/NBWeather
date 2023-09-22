package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerViewData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

data class ForecastAlertsViewData(
    override val items: List<ForecastAlertsAlert>
) : NBPagerViewData<Unit, ForecastAlertsAlert> {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): ForecastAlertsViewData? {
            val items = oneCall.nationalWeatherAlerts.mapNotNull { nationalWeatherAlert ->
                ForecastAlertsAlert.from(
                    timezoneOffset = oneCall.timezoneOffset,
                    nationalWeatherAlert = nationalWeatherAlert
                )
            }
            return nbNullSafeList(items) { i ->
                ForecastAlertsViewData(
                    items = i
                )
            }
        }

    }

}