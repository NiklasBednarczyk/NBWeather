package de.niklasbednarczyk.nbweather.feature.location.cards.models.overview

import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.nbweather.feature.location.extensions.icon

data class LocationCardOverviewWeatherModel(
    val temperature: NBString?,
    val feelsLikeTemperature: NBString?,
    val feelsLikePrefix: NBString?,
    val temperatureUnit: NBString?,
    val weatherDescription: NBString?,
    val weatherIcon: NBIconModel
) {

    companion object {

        fun from(
            units: NBUnitsType,
            temperature: TemperatureValue?,
            feelsLikeTemperature: TemperatureValue?,
            weather: WeatherModelData?,
            ): LocationCardOverviewWeatherModel? {
            val feelsLikePrefix = NBString.Resource(R.string.value_temperature_feels_like_prefix)

            return nbNullSafe(
                temperature,
                feelsLikeTemperature,
                weather?.description,
                weather?.icon
            ) { temperatureValue, feelsLikeTemperatureValue, weatherDescription, weatherIcon ->

                LocationCardOverviewWeatherModel(
                    temperature = temperatureValue.displayValue,
                    feelsLikeTemperature = feelsLikeTemperatureValue.displayValueWithShortUnit,
                    feelsLikePrefix = feelsLikePrefix,
                    temperatureUnit = temperatureValue.getUnit(units),
                    weatherDescription = weatherDescription.value,
                    weatherIcon = weatherIcon.type.icon
                )
            }

        }

    }


}