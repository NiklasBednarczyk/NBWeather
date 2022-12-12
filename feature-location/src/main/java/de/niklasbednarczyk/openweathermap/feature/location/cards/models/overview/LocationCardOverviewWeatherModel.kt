package de.niklasbednarczyk.openweathermap.feature.location.cards.models.overview

import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.openweathermap.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.openweathermap.feature.location.extensions.icon

data class LocationCardOverviewWeatherModel(
    val temperature: OwmString?,
    val feelsLikeTemperature: OwmString?,
    val feelsLikePrefix: OwmString?,
    val temperatureUnit: OwmString?,
    val weatherDescription: OwmString?,
    val weatherIcon: OwmIconModel
) {

    companion object {

        fun from(
            units: OwmUnitsType,
            temperature: TemperatureValue?,
            feelsLikeTemperature: TemperatureValue?,
            weather: WeatherModelData?,
            ): LocationCardOverviewWeatherModel? {
            val feelsLikePrefix = OwmString.Resource(R.string.value_temperature_feels_like_prefix)

            return owmNullSafe(
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