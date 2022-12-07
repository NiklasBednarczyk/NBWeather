package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCard
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridRow
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementDefault
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodaySunAndMoonModel


@Composable
fun LocationOverviewTodaySunAndMoonView(
    sunAndMoon: LocationOverviewTodaySunAndMoonModel
) {
    OwmCard(OwmString.Resource(R.string.screen_location_overview_today_card_sun_and_moon_title)) {
        Column(
            verticalArrangement = columnVerticalArrangementDefault,
        ) {
            OwmGridRow(
                items = sunAndMoon.sunItems
            )
            OwmGridRow(
                items = sunAndMoon.moonItems
            )
        }

    }
}
