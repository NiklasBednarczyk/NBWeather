package de.niklasbednarczyk.openweathermap.feature.location.cards.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueIconModel.Companion.toValueIcon
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueItem
import de.niklasbednarczyk.openweathermap.data.onecall.values.units.*
import de.niklasbednarczyk.openweathermap.data.onecall.values.winddegrees.WindDegreesValue
import de.niklasbednarczyk.openweathermap.feature.location.extensions.displayText

data class LocationCardWeatherModel(
    override val cardTitle: OwmString?,
    val items: List<OwmGridItem>
) : LocationCardItem {

    companion object {

        fun from(
            units: OwmUnitsType,
            pressure: PressureValue?,
            humidity: PercentValue?,
            dewPointTemperature: TemperatureValue?,
            cloudiness: PercentValue?,
            uvIndex: UVIndexValue?,
            visibility: DistanceValue?,
            windSpeed: WindSpeedValue?,
            windGust: WindSpeedValue?,
            windDegrees: WindDegreesValue?,
            rain1hVolume: PrecipitationValue?,
            snow1hVolume: PrecipitationValue?,
            probabilityOfPrecipitation: ProbabilityValue?
        ): LocationCardWeatherModel? {
            val cardTitle = OwmString.Resource(R.string.screen_location_card_weather_title)

            val items = mutableListOf<OwmGridItem>()

            owmNullSafe(probabilityOfPrecipitation) { probabilityOfPrecipitationValue ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_weather_value_probability_of_precipitation),
                        valueIcon = OwmIcons.ProbabilityOfPrecipitation.toValueIcon(),
                        value = OwmValueItem.Texts(
                            probabilityOfPrecipitationValue.displayValue,
                            probabilityOfPrecipitationValue.unit
                        )
                    )
                )
            }

            owmNullSafe(rain1hVolume) { rain1hVolumeValue ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_weather_value_rain),
                        valueIcon = OwmIcons.Rain.toValueIcon(),
                        value = OwmValueItem.Texts(
                            rain1hVolumeValue.displayValue,
                            rain1hVolumeValue.unit
                        )
                    )
                )
            }

            owmNullSafe(snow1hVolume) { snow1hVolumeValue ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_weather_value_snow),
                        valueIcon = OwmIcons.Snow.toValueIcon(),
                        value = OwmValueItem.Texts(
                            snow1hVolumeValue.displayValue,
                            snow1hVolumeValue.unit
                        )
                    )
                )
            }

            owmNullSafe(windSpeed) { windSpeedValue ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_weather_value_wind_speed),
                        valueIcon = OwmIcons.WindSpeed.toValueIcon(),
                        value = OwmValueItem.Texts(
                            windSpeedValue.displayValue,
                            windSpeedValue.getUnit(units)
                        )
                    )
                )
            }

            owmNullSafe(windGust) { windGustValue ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_weather_value_wind_gust),
                        valueIcon = OwmIcons.WindGust.toValueIcon(),
                        value = OwmValueItem.Texts(
                            windGustValue.displayValue,
                            windGustValue.getUnit(units)
                        )
                    )
                )
            }

            owmNullSafe(windDegrees) { windDegreesValue ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_weather_value_wind_direction),
                        valueIcon = OwmIcons.WindDegrees.toValueIcon(windDegreesValue.rotationDegrees),
                        value = OwmValueItem.Texts(
                            windDegreesValue.type?.displayText ?: return@owmNullSafe null
                        )
                    )
                )
            }

            owmNullSafe(pressure) { pressureValue ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_weather_value_pressure),
                        valueIcon = OwmIcons.Pressure.toValueIcon(),
                        value = OwmValueItem.Texts(
                            pressureValue.displayValue,
                            pressureValue.unit
                        )
                    )
                )
            }

            owmNullSafe(humidity) { humidityValue ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_weather_value_humidity),
                        valueIcon = OwmIcons.Humidity.toValueIcon(),
                        value = OwmValueItem.Texts(
                            humidityValue.displayValue,
                            humidityValue.unit
                        )
                    )
                )
            }

            owmNullSafe(dewPointTemperature) { dewPointTemperatureValue ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_weather_value_dew_point),
                        valueIcon = OwmIcons.DewPoint.toValueIcon(),
                        value = OwmValueItem.Texts(
                            dewPointTemperatureValue.displayValue,
                            dewPointTemperatureValue.getUnit(units)
                        )
                    )
                )
            }

            owmNullSafe(cloudiness) { cloudinessValue ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_weather_value_cloudiness),
                        valueIcon = OwmIcons.Cloudiness.toValueIcon(),
                        value = OwmValueItem.Texts(
                            cloudinessValue.displayValue,
                            cloudinessValue.unit
                        )
                    )
                )
            }

            owmNullSafe(uvIndex) { uvIndexValue ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_weather_value_uv_index),
                        valueIcon = OwmIcons.UVIndex.toValueIcon(),
                        value = OwmValueItem.Texts(
                            uvIndexValue.displayValue,
                            uvIndexValue.unit
                        )
                    )
                )
            }

            owmNullSafe(visibility) { visibilityValue ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_weather_value_visibility),
                        valueIcon = OwmIcons.Visibility.toValueIcon(),
                        value = OwmValueItem.Texts(
                            visibilityValue.displayValue,
                            visibilityValue.unit
                        )
                    )
                )
            }

            return owmNullSafeList(
                items
            ) { i ->
                LocationCardWeatherModel(
                    cardTitle = cardTitle,
                    items = i
                )
            }
        }

    }


}