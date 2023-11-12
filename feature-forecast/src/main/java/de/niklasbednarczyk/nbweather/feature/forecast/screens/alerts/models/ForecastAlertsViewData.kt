package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerViewData
import de.niklasbednarczyk.nbweather.data.onecall.models.NationalWeatherAlertModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

data class ForecastAlertsViewData(
    override val items: List<ForecastAlertsAlertModel>
) : NBPagerViewData<Unit, ForecastAlertsAlertModel> {

    override val initialKey: Unit? = null

    override fun getItemKey(item: ForecastAlertsAlertModel) {
        return
    }

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): ForecastAlertsViewData? {
            return from(
                timezoneOffset = oneCall.timezoneOffset,
                nationalWeatherAlerts = oneCall.nationalWeatherAlerts
            )
        }

        fun from(
            timezoneOffset: NBTimezoneOffsetValue?,
            nationalWeatherAlerts: List<NationalWeatherAlertModelData>
        ): ForecastAlertsViewData? {
            val items = nationalWeatherAlerts.mapNotNull { nationalWeatherAlert ->
                ForecastAlertsAlertModel.from(
                    nationalWeatherAlert = nationalWeatherAlert,
                    timezoneOffset = timezoneOffset
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