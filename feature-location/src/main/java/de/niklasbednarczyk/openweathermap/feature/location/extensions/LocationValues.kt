package de.niklasbednarczyk.openweathermap.feature.location.extensions

import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueItem
import de.niklasbednarczyk.openweathermap.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueIconModel.Companion.toValueIcon
import de.niklasbednarczyk.openweathermap.data.onecall.values.units.ProbabilityValue

fun temperatureWithFeelsLikeValue(
    temperature: TemperatureValue,
    feelsLikeTemperature: TemperatureValue
): OwmValueItem {
    val formatResId = R.string.format_temperature_feels_like

    return OwmValueItem.TextsWithFormat(
        temperature.displayValueWithShortUnit,
        feelsLikeTemperature.displayValueWithShortUnit,
        formatResId = formatResId
    )
}

fun probabilityOfPrecipitationValue(
    probabilityOfPrecipitation: ProbabilityValue
): OwmValueItem {
    return OwmValueItem.IconWithTexts(
        valueIcon = OwmIcons.ProbabilityOfPrecipitation.toValueIcon(),
        probabilityOfPrecipitation.displayValue,
        probabilityOfPrecipitation.unit
    )
}