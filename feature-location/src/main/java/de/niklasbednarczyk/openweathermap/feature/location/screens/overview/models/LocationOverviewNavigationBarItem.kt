package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.navigationbar.OwmNavigationBarItem

enum class LocationOverviewNavigationBarItem : OwmNavigationBarItem {
    TODAY,
    HOURLY,
    DAILY;

    override val icon: OwmIconModel
        get() = when (this) {
            TODAY -> OwmIcons.Today
            HOURLY -> OwmIcons.Hourly
            DAILY -> OwmIcons.Daily
        }

    override val label: OwmString
        get() = when (this) {
            TODAY -> OwmString.Resource(R.string.screen_location_overview_tab_today)
            HOURLY -> OwmString.Resource(R.string.screen_location_overview_tab_hourly)
            DAILY -> OwmString.Resource(R.string.screen_location_overview_tab_daily)
        }

}