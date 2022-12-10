package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models

import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem.Companion.owmEmptyList
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.hourly.LocationOverviewHourlyModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayItem

data class LocationOverviewViewData(
    val todayItems: List<OwmListItem<LocationOverviewTodayItem>>,
    val hourlyItems: List<OwmListItem<LocationOverviewHourlyModel>>
) {

    companion object {

        fun empty(): LocationOverviewViewData {
            return LocationOverviewViewData(
                todayItems = owmEmptyList(4),
                hourlyItems = owmEmptyList(3)
            )
        }

    }

}
