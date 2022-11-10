package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.text.OwmTextCombined
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayHeaderModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.header.LocationOverviewTodayHeaderWeatherModel

@Composable
fun LocationOverviewTodayHeaderView(
    header: LocationOverviewTodayHeaderModel
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Weather(
            modifier = Modifier.align(Alignment.Center),
            weather = header.weather
        )
    }

}

@Composable
private fun Weather(
    modifier: Modifier = Modifier,
    weather: LocationOverviewTodayHeaderWeatherModel
) {
    Column(
        modifier = modifier.width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OwmIcon(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f),
            icon = weather.weatherIcon
        )
        Text(
            text = weather.weatherDescription.asString(),
            style = MaterialTheme.typography.titleLarge
        )
        OwmTextCombined(
            MaterialTheme.typography.displayLarge,
            weather.currentTemperature,
            weather.temperatureUnit
        )
        OwmTextCombined(
            MaterialTheme.typography.titleLarge,
            weather.feelsLikePrefix,
            weather.feelsLikeTemperature
        )
    }
}

