package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import de.niklasbednarczyk.nbweather.core.ui.dimens.rowHorizontalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewSummaryModel
import de.niklasbednarczyk.nbweather.feature.forecast.views.LimitTemperaturesView
import de.niklasbednarczyk.nbweather.feature.forecast.views.WeatherView

@Composable
fun ForecastOverviewSummaryView(
    summary: ForecastOverviewSummaryModel
) {
    CurrentTemperature(
        currentTemperature = summary.currentTemperature
    )
    WeatherView(
        weatherCondition = summary.weatherCondition,
        weatherIcon = summary.weatherIcon
    )
    LimitTemperaturesAndTime(
        minTemperature = summary.minTemperature,
        maxTemperature = summary.maxTemperature,
        currentTime = summary.currentTime
    )
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
        style = MaterialTheme.typography.titleSmall,
        textAlign = TextAlign.End
    )
}

@Composable
fun LimitTemperaturesAndTime(
    minTemperature: TemperatureForecastValue,
    maxTemperature: TemperatureForecastValue,
    currentTime: NBDateTimeDisplayModel
) {
    Row(
        horizontalArrangement = rowHorizontalArrangementBig,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LimitTemperaturesView(
            minTemperature = minTemperature,
            maxTemperature = maxTemperature
        )
        CurrentTime(
            currentTime = currentTime
        )
    }
}
