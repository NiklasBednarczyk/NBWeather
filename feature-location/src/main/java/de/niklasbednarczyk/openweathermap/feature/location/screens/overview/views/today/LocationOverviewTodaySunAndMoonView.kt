package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCard
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangement
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodaySunAndMoonModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.sunandmoon.LocationOverviewTodaySunAndMoonItem

//TODO (#9) Make grid row view for current weather, temperatures and sunAndMoon

@Composable
fun LocationOverviewTodaySunAndMoonView(
    sunAndMoon: LocationOverviewTodaySunAndMoonModel
) {
    OwmCard(OwmString.Resource(R.string.screen_location_overview_today_card_sun_and_moon_title)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(columnVerticalArrangement),
        ) {
            SunAndMoonRow(
                items = sunAndMoon.sunItems
            )
            SunAndMoonRow(
                items = sunAndMoon.moonItems
            )
        }

    }
}

@Composable
private fun SunAndMoonRow(
    items: List<LocationOverviewTodaySunAndMoonItem>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
    ) {
        items.forEach { item ->

            val modifier = Modifier
                .weight(1f)
                .fillMaxHeight()

            when (item) {
                is LocationOverviewTodaySunAndMoonItem.Icon -> {
                    Icon(
                        modifier = modifier,
                        icon = item
                    )
                }
                is LocationOverviewTodaySunAndMoonItem.Placeholder -> {
                    Spacer(modifier = modifier)
                }
            }
        }
    }
}

@Composable
private fun Icon(
    modifier: Modifier = Modifier,
    icon: LocationOverviewTodaySunAndMoonItem.Icon
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = icon.title.asString(),
            style = MaterialTheme.typography.titleSmall
        )
        OwmIcon(
            icon = icon.icon
        )
        Text(
            text = icon.value.asString(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
