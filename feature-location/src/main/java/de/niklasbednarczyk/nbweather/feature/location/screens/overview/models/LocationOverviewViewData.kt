package de.niklasbednarczyk.nbweather.feature.location.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardItem
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.daily.LocationOverviewDailyModel
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.hourly.LocationOverviewHourlyModel

data class LocationOverviewViewData(
    val todayCardItems: List<LocationCardItem>,
    val hourlyMap: Map<NBString?, List<LocationOverviewHourlyModel>>,
    val dailyModels: List<LocationOverviewDailyModel>
)