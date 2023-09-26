package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString

sealed interface ForecastAlertsAlertInfoItem {

    data class Dates(
        val startDate: NBDateTimeDisplayModel,
        val endDate: NBDateTimeDisplayModel
    ) : ForecastAlertsAlertInfoItem

    data class Description(
        val text: NBString
    ) : ForecastAlertsAlertInfoItem

    data class EventName(
        val text: NBString
    ) : ForecastAlertsAlertInfoItem

    data class SenderName(
        val text: NBString
    ) : ForecastAlertsAlertInfoItem

    data class Tags(
        val tags: List<NBString>
    ) : ForecastAlertsAlertInfoItem

}