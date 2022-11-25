package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCard
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangement
import de.niklasbednarczyk.openweathermap.core.ui.theme.spacerTextWidth
import de.niklasbednarczyk.openweathermap.core.ui.windowsize.getWidthSizeClass
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayCurrentWeatherModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.currentweather.LocationOverviewTodayCurrentWeatherItem

@Composable
fun LocationOverviewTodayCurrentWeatherView(
    currentWeather: LocationOverviewTodayCurrentWeatherModel
) {
    val rowItemCount = when (getWidthSizeClass()) {
        WindowWidthSizeClass.Compact -> 3
        WindowWidthSizeClass.Medium -> 4
        WindowWidthSizeClass.Expanded -> 5
        else -> 3
    }

    OwmCard(OwmString.Resource(R.string.screen_location_overview_today_card_current_weather_title)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(columnVerticalArrangement),
        ) {
            currentWeather.items.chunked(rowItemCount).forEach { rowItems ->
                Row {
                    rowItems.forEach { item ->
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = item.title.asString(),
                                style = MaterialTheme.typography.titleSmall
                            )
                            OwmIcon(icon = item.icon)
                            Row(
                                verticalAlignment = Alignment.Bottom
                            ) {
                                when (item) {
                                    is LocationOverviewTodayCurrentWeatherItem.Icon -> {
                                        OwmIcon(
                                            modifier = Modifier.rotate(item.rotationDegrees),
                                            icon = item.value
                                        )
                                    }
                                    is LocationOverviewTodayCurrentWeatherItem.Text -> {
                                        Text(
                                            text = item.value.asString(),
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }
                                }
                                Spacer(
                                    modifier = Modifier.width(spacerTextWidth)
                                )
                                Text(
                                    text = item.unit.asString(),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }

                    val remainingNeededItems = rowItemCount - rowItems.size
                    repeat(remainingNeededItems) {
                        Spacer(modifier = Modifier.weight(1f))
                    }

                }
            }
        }


    }


}