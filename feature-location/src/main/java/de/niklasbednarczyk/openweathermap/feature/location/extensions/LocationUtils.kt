package de.niklasbednarczyk.openweathermap.feature.location.extensions

import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridValueItem
import de.niklasbednarczyk.openweathermap.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.openweathermap.core.ui.R

fun temperatureWithFeelsLikeGridValue(
    temperature: TemperatureValue,
    feelsLikeTemperature: TemperatureValue
): OwmGridValueItem {
    val formatResId = R.string.format_temperature_feels_like

    return OwmGridValueItem.TextsWithFormat(
        temperature.displayValueWithShortUnit,
        feelsLikeTemperature.displayValueWithShortUnit,
        formatResId = formatResId
    )
}