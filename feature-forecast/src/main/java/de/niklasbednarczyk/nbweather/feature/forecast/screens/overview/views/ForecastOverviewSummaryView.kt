package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.ui.common.displayValueWithSymbol
import de.niklasbednarczyk.nbweather.core.ui.common.time
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.dimens.rowHorizontalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.rowHorizontalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.feature.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.extensions.icon
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewSummaryModel

@Composable
fun ForecastOverviewSummaryView(
    summary: ForecastOverviewSummaryModel
) {
    CurrentTemperature(
        currentTemperature = summary.currentTemperature
    )
    Column(
        verticalArrangement = columnVerticalArrangementSmall
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            horizontalArrangement = rowHorizontalArrangementBig,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WeatherIcon(
                weatherIcon = summary.weatherIcon
            )
            WeatherCondition(
                weatherCondition = summary.weatherCondition
            )
        }
    }
    Row(
        horizontalArrangement = rowHorizontalArrangementBig
    ) {
        LimitTemperature(
            temperature = summary.maxTemperature,
            icon = NBIcons.MaxTemperature
        )
        LimitTemperature(
            temperature = summary.minTemperature,
            icon = NBIcons.MinTemperature
        )
        CurrentTime(
            currentTime = summary.currentTime
        )
    }
}

@Composable
private fun CurrentTemperature(
    currentTemperature: NBUnitsValue
) {
    Text(
        text = currentTemperature.displayValueWithSymbol.asString(),
        style = MaterialTheme.typography.displayLarge
    )
}

@Composable
private fun RowScope.CurrentTime(
    currentTime: NBDateTimeDisplayModel
) {
    Text(
        modifier = Modifier.weight(1f),
        text = currentTime.time.asString(),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.End
    )
}

@Composable
private fun LimitTemperature(
    temperature: NBUnitsValue,
    icon: NBIconModel
) {
    Row(
        horizontalArrangement = rowHorizontalArrangementSmall,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NBIcon(
            icon = icon
        )
        Text(
            text = temperature.displayValueWithSymbol.asString(),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun WeatherCondition(
    weatherCondition: WeatherConditionType
) {
    Text(
        text = weatherCondition.displayText.asString(),
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun WeatherIcon(
    weatherIcon: WeatherIconType
) {
    NBIcon(
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1f),
        icon = weatherIcon.icon
    )
}
