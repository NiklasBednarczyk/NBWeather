package de.niklasbednarczyk.nbweather.feature.forecast.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.dimens.rowHorizontalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconView
import de.niklasbednarczyk.nbweather.core.ui.icons.nbIconFillHeight
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon

@Composable
fun WeatherView(
    weatherCondition: WeatherConditionType,
    weatherIcon: WeatherIconType
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
                weatherCondition = weatherCondition
            )
        }
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
    NBIconView(
        modifier = Modifier.nbIconFillHeight(),
        icon = weatherIcon.icon
    )
}
