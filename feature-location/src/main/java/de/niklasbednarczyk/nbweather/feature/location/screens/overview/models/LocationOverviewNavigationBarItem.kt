package de.niklasbednarczyk.nbweather.feature.location.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.scaffold.navigationbar.NBNavigationBarItem

enum class LocationOverviewNavigationBarItem : NBNavigationBarItem {
    TODAY,
    HOURLY,
    DAILY;

    override val icon: NBIconModel.FilledAndOutlined
        get() = when (this) {
            TODAY -> NBIcons.Today
            HOURLY -> NBIcons.Hourly
            DAILY -> NBIcons.Daily
        }

    override val label: NBString
        get() = when (this) {
            TODAY -> NBString.Resource(R.string.screen_location_overview_tab_today)
            HOURLY -> NBString.Resource(R.string.screen_location_overview_tab_hourly)
            DAILY -> NBString.Resource(R.string.screen_location_overview_tab_daily)
        }

}