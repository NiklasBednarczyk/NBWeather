package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCardItem
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem.Companion.toOwmList
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

sealed interface LocationOverviewTodayItem : OwmCardItem {

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType
        ): List<OwmListItem<LocationOverviewTodayItem>> {
            val items = mutableListOf<LocationOverviewTodayItem?>()

            items.add(LocationOverviewTodayOverviewModel.from(oneCall, timeFormat))

            items.add(LocationOverviewTodayCurrentWeatherModel.from(oneCall))

            items.add(LocationOverviewTodayTemperaturesModel.from(oneCall))

            items.add(LocationOverviewTodaySunAndMoonModel.from(oneCall, timeFormat))

            return items.filterNotNull().toOwmList()
        }


    }


}