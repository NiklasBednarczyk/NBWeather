package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models

import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerViewData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

data class ForecastAlertsViewData(
    override val items: List<ForecastAlertsAlert>
) : NBPagerViewData<ForecastAlertsAlert, Unit> {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): ForecastAlertsViewData {
            val items = oneCall.nationalWeatherAlerts.map { nationalWeatherAlert ->
                ForecastAlertsAlert.from(
                    timezoneOffset = oneCall.timezoneOffset,
                    nationalWeatherAlert = nationalWeatherAlert
                )
            }
            return ForecastAlertsViewData(
                items = items
            )
        }

    }

}