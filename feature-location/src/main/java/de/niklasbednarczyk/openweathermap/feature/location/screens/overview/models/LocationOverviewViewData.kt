package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models

import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayItem

data class LocationOverviewViewData(
    val todayItems: List<LocationOverviewTodayItem>
)
