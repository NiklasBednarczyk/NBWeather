package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCard
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.text.combinedTemperature
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangement
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayTemperaturesModel

@Composable
fun LocationOverviewTodayTemperaturesView(
    temperatures: LocationOverviewTodayTemperaturesModel
) {
    OwmCard(OwmString.Resource(R.string.screen_location_overview_today_card_temperatures_title)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(columnVerticalArrangement)
        ) {
            Row {
                temperatures.thresholdItems.forEach { threshold ->
                    Temperature(
                        title = threshold.title,
                        temperature = threshold.temperature
                    )
                }
            }
            Row {
                temperatures.dayItems.forEach { day ->
                    val combinedTemperature = combinedTemperature(
                        temperature = day.temperature,
                        feelsLikeTemperature = day.feelsLikeTemperature
                    )
                    Temperature(
                        title = day.title,
                        temperature = combinedTemperature
                    )
                }
            }
        }
    }

}

@Composable
private fun RowScope.Temperature(
    title: OwmString?,
    temperature: OwmString?
) {
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title.asString(),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = temperature.asString(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}