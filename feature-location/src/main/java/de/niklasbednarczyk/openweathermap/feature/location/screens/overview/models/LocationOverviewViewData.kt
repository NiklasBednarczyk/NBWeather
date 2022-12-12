package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.daily.LocationOverviewDailyModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.hourly.LocationOverviewHourlyModel
import de.niklasbednarczyk.openweathermap.feature.location.cards.models.LocationCardItem

data class LocationOverviewViewData(
    val todayCardItems: List<LocationCardItem>,
    val hourlyMap: Map<OwmString?, List<LocationOverviewHourlyModel>>,
    val dailyModels: List<LocationOverviewDailyModel>
)