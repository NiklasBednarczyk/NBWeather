package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
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
import de.niklasbednarczyk.nbweather.core.ui.icons.nbIconFillHeight
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesValue
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewSummaryModel

@Composable
fun ForecastOverviewSummaryView(
    summary: ForecastOverviewSummaryModel
) {
    CurrentTemperature(
        currentTemperature = summary.currentTemperature,
        style = MaterialTheme.typography.displayLarge
    )
    Weather(
        weatherCondition = summary.weatherCondition,
        weatherIcon = summary.weatherIcon,
        style = MaterialTheme.typography.titleLarge
    )
    Wind(
        windDegrees = summary.windDegrees,
        windSpeed = summary.windSpeed,
        style = MaterialTheme.typography.titleMedium
    )
    LimitTemperaturesAndTime(
        minTemperature = summary.minTemperature,
        maxTemperature = summary.maxTemperature,
        currentTime = summary.currentTime,
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
private fun CurrentTemperature(
    currentTemperature: NBUnitsValue,
    style: TextStyle
) {
    Text(
        text = currentTemperature.displayValueWithSymbol.asString(),
        style = style
    )
}

@Composable
private fun RowScope.CurrentTime(
    currentTime: NBDateTimeDisplayModel,
    style: TextStyle
) {
    Text(
        modifier = Modifier.weight(1f),
        text = currentTime.time.asString(),
        style = style,
        textAlign = TextAlign.End
    )
}

@Composable
private fun LimitTemperature(
    temperature: NBUnitsValue,
    icon: NBIconModel,
    style: TextStyle
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
            style = style
        )
    }
}

@Composable
fun LimitTemperaturesAndTime(
    minTemperature: NBUnitsValue,
    maxTemperature: NBUnitsValue,
    currentTime: NBDateTimeDisplayModel,
    style: TextStyle
) {
    Row(
        horizontalArrangement = rowHorizontalArrangementBig,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LimitTemperature(
            temperature = maxTemperature,
            icon = NBIcons.MaxTemperature,
            style = style
        )
        LimitTemperature(
            temperature = minTemperature,
            icon = NBIcons.MinTemperature,
            style = style
        )
        CurrentTime(
            currentTime = currentTime,
            style = style
        )
    }
}

@Composable
private fun Weather(
    weatherCondition: WeatherConditionType,
    weatherIcon: WeatherIconType,
    style: TextStyle
) {
    Column(
        verticalArrangement = columnVerticalArrangementSmall
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            horizontalArrangement = rowHorizontalArrangementBig,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WeatherIcon(
                weatherIcon = weatherIcon
            )
            WeatherCondition(
                weatherCondition = weatherCondition,
                style = style
            )
        }
    }
}

@Composable
private fun WeatherCondition(
    weatherCondition: WeatherConditionType,
    style: TextStyle
) {
    Text(
        text = weatherCondition.displayText.asString(),
        style = style
    )
}

@Composable
private fun WeatherIcon(
    weatherIcon: WeatherIconType
) {
    NBIcon(
        modifier = Modifier.nbIconFillHeight(),
        icon = weatherIcon.icon
    )
}

@Composable
private fun Wind(
    windDegrees: WindDegreesValue,
    windSpeed: NBUnitsValue,
    style: TextStyle
) {
    Row(
        horizontalArrangement = rowHorizontalArrangementBig,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WindDegrees(
            windDegrees = windDegrees,
            style = style
        )
        WindSpeed(
            windSpeed = windSpeed,
            style = style
        )
    }
}


@Composable
private fun WindDegrees(
    windDegrees: WindDegreesValue,
    style: TextStyle
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = rowHorizontalArrangementSmall
    ) {
        NBIcon(
            modifier = Modifier.rotate(windDegrees.rotationDegrees),
            icon = NBIcons.WindDegrees
        )
        Text(
            text = windDegrees.type?.displayText.asString(),
            style = style
        )
    }
}

@Composable
private fun WindSpeed(
    windSpeed: NBUnitsValue,
    style: TextStyle
) {
    Text(
        text = windSpeed.displayValueWithSymbol.asString(),
        style = style
    )
}