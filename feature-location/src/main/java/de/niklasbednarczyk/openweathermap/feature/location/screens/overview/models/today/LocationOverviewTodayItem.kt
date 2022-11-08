package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

sealed interface LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): List<LocationOverviewTodayItem> {
            val items = mutableListOf<LocationOverviewTodayItem?>()

            items.add(LocationOverviewTodayAlertModel.from(oneCall.nationalWeatherAlerts))

            return items.filterNotNull()
        }


    }


}