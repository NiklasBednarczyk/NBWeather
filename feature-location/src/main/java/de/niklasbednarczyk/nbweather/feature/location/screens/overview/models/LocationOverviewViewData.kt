package de.niklasbednarczyk.nbweather.feature.location.screens.overview.models

import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardItem
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.daily.LocationOverviewDailyModel
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.hourly.LocationOverviewHourlyModel
import java.time.DayOfWeek

data class LocationOverviewViewData(
    val todayCardItems: List<LocationCardItem>,
    val hourlyMap: Map<DayOfWeek?, List<LocationOverviewHourlyModel>>,
    val dailyModels: List<LocationOverviewDailyModel>
)