package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

sealed interface LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType
        ): List<LocationOverviewTodayItem> {
            val items = mutableListOf<LocationOverviewTodayItem?>()

            items.add(LocationOverviewTodayAlertModel.from(oneCall))

            items.add(LocationOverviewTodayHeaderModel.from(oneCall, timeFormat))

            items.add(LocationOverviewTodayCurrentWeatherModel.from(oneCall))

            return items.filterNotNull()
        }


    }


}