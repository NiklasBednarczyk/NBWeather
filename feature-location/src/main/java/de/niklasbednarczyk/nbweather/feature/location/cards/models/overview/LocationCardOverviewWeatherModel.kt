package de.niklasbednarczyk.nbweather.feature.location.cards.models.overview

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.nbweather.feature.location.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.location.extensions.icon

data class LocationCardOverviewWeatherModel(
    val temperature: NBUnitsValue?,
    private val feelsLikeTemperature: NBUnitsValue?,
    val weatherCondition: NBString?,
    val weatherIcon: NBIconModel
) {

    val feelsLikeTemperatureText: NBString
        @Composable
        get() = NBString.Resource(
            R.string.format_space_2_items,
            NBString.Resource(R.string.screen_location_card_overview_value_temperature_feels_like),
            feelsLikeTemperature?.getDisplayValueWithSymbol(NBSettings.units)
        )

    companion object {

        fun from(
            temperature: TemperatureValue?,
            feelsLikeTemperature: TemperatureValue?,
            weather: WeatherModelData?,
        ): LocationCardOverviewWeatherModel? {
            return nbNullSafe(
                temperature,
                feelsLikeTemperature,
                weather?.condition,
                weather?.icon
            ) { temperatureValue, feelsLikeTemperatureValue, weatherCondition, weatherIcon ->

                LocationCardOverviewWeatherModel(
                    temperature = temperatureValue.getLong(),
                    feelsLikeTemperature = feelsLikeTemperatureValue.getShort(),
                    weatherCondition = weatherCondition.displayText,
                    weatherIcon = weatherIcon.icon
                )
            }

        }

    }


}