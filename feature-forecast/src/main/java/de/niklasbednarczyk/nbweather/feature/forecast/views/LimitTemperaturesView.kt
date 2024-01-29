package de.niklasbednarczyk.nbweather.feature.forecast.views

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import de.niklasbednarczyk.nbweather.core.ui.common.displayValueWithSymbol
import de.niklasbednarczyk.nbweather.core.ui.dimens.rowHorizontalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.rowHorizontalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue

@Composable
fun LimitTemperaturesView(
    minTemperature: TemperatureForecastValue,
    maxTemperature: TemperatureForecastValue
) {
    Row(
        horizontalArrangement = rowHorizontalArrangementBig,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LimitTemperature(
            temperature = maxTemperature,
            icon = NBIcons.MaxTemperature
        )
        LimitTemperature(
            temperature = minTemperature,
            icon = NBIcons.MinTemperature
        )
    }
}

@Composable
private fun LimitTemperature(
    temperature: TemperatureForecastValue,
    icon: NBIconItem
) {
    Row(
        horizontalArrangement = rowHorizontalArrangementSmall,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NBIconView(
            icon = icon
        )
        Text(
            text = temperature.unitsValue.toShort().displayValueWithSymbol.asString(),
            style = MaterialTheme.typography.titleMedium
        )
    }
}
