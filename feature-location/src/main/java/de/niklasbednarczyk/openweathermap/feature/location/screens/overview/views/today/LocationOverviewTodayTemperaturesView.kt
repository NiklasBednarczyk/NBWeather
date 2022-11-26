package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCard
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.text.combinedTemperature
import de.niklasbednarczyk.openweathermap.core.ui.theme.spacerTextWidth
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayTemperaturesModel

@Composable
fun LocationOverviewTodayTemperaturesView(
    temperatures: LocationOverviewTodayTemperaturesModel
) {
    OwmCard(OwmString.Resource(R.string.screen_location_overview_today_card_temperatures_title)) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                temperatures.thresholdItems.forEach { threshold ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = threshold.title.asString(),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.width(spacerTextWidth))
                        Text(
                            text = threshold.temperature.asString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Normal
                        )
                    }

                }
            }
            Row {
                temperatures.dayItems.forEach { day ->
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val combinedTemperature = combinedTemperature(
                            temperature = day.temperature,
                            feelsLikeTemperature = day.feelsLikeTemperature
                        )
                        Text(
                            text = day.title.asString(),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = combinedTemperature.asString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }


}