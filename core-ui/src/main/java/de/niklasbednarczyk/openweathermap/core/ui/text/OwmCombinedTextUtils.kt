package de.niklasbednarczyk.openweathermap.core.ui.text

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString

@Composable
fun combinedTemperature(
    temperature: OwmString?,
    feelsLikeTemperature: OwmString?
): OwmString {
    val temperatureText = temperature.asString()
    val feelsLikeTemperatureText = feelsLikeTemperature.asString()
    return OwmString.Resource(
        R.string.format_temperature_feels_like,
        temperatureText,
        feelsLikeTemperatureText
    )
}