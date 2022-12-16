package de.niklasbednarczyk.nbweather.feature.location.extensions

import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueIconModel.Companion.toValueIcon
import de.niklasbednarczyk.nbweather.data.onecall.values.units.ProbabilityValue

fun temperatureWithFeelsLikeValue(
    temperature: TemperatureValue,
    feelsLikeTemperature: TemperatureValue
): NBValueItem {
    val formatResId = R.string.format_temperature_feels_like

    return NBValueItem.TextsWithFormat(
        temperature.displayValueWithShortUnit,
        feelsLikeTemperature.displayValueWithShortUnit,
        formatResId = formatResId
    )
}

fun probabilityOfPrecipitationValue(
    probabilityOfPrecipitation: ProbabilityValue
): NBValueItem {
    return NBValueItem.IconWithTexts(
        valueIcon = NBIcons.ProbabilityOfPrecipitation.toValueIcon(),
        probabilityOfPrecipitation.displayValue,
        probabilityOfPrecipitation.unit
    )
}