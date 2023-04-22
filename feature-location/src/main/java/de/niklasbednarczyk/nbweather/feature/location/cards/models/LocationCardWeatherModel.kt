package de.niklasbednarczyk.nbweather.feature.location.cards.models

import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueIconModel.Companion.toValueIcon
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.data.onecall.values.units.*
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesValue
import de.niklasbednarczyk.nbweather.feature.location.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.location.extensions.toValueItemWithUnit

data class LocationCardWeatherModel(
    override val cardTitle: NBString?,
    val items: List<NBGridItem.ThreeLines?>
) : LocationCardItem {

    companion object {

        fun from(
            units: NBUnitsType,
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
            val cardTitle = NBString.Resource(R.string.screen_location_card_weather_title)

            val items = mutableListOf<NBGridItem.ThreeLines?>()

            nbNullSafe(probabilityOfPrecipitation) { probabilityOfPrecipitationValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_probability_of_precipitation),
                        valueIcon = NBIcons.ProbabilityOfPrecipitation.toValueIcon(),
                        value = NBValueItem.Texts(
                            probabilityOfPrecipitationValue.displayValue,
                            probabilityOfPrecipitationValue.unit
                        )
                    )
                )
            }

            nbNullSafe(rain1hVolume) { rain1hVolumeValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_rain),
                        valueIcon = NBIcons.Rain.toValueIcon(),
                        value = NBValueItem.Texts(
                            rain1hVolumeValue.displayValue,
                            rain1hVolumeValue.unit
                        )
                    )
                )
            }

            nbNullSafe(snow1hVolume) { snow1hVolumeValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_snow),
                        valueIcon = NBIcons.Snow.toValueIcon(),
                        value = NBValueItem.Texts(
                            snow1hVolumeValue.displayValue,
                            snow1hVolumeValue.unit
                        )
                    )
                )
            }

            nbNullSafe(windSpeed) { windSpeedValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_wind_speed),
                        valueIcon = NBIcons.WindSpeed.toValueIcon(),
                        value = NBValueItem.Texts(
                            windSpeedValue.displayValue,
                            windSpeedValue.getUnit(units)
                        )
                    )
                )
            }

            nbNullSafe(windGust) { windGustValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_wind_gust),
                        valueIcon = NBIcons.WindGust.toValueIcon(),
                        value = NBValueItem.Texts(
                            windGustValue.displayValue,
                            windGustValue.getUnit(units)
                        )
                    )
                )
            }

            nbNullSafe(windDegrees) { windDegreesValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_wind_direction),
                        valueIcon = NBIcons.WindDegrees.toValueIcon(windDegreesValue.rotationDegrees),
                        value = NBValueItem.Texts(
                            windDegreesValue.type?.displayText ?: return@nbNullSafe null
                        )
                    )
                )
            }

            nbNullSafe(pressure) { pressureValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_pressure),
                        valueIcon = NBIcons.Pressure.toValueIcon(),
                        value = NBValueItem.Texts(
                            pressureValue.displayValue,
                            pressureValue.unit
                        )
                    )
                )
            }

            nbNullSafe(humidity) { humidityValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_humidity),
                        valueIcon = NBIcons.Humidity.toValueIcon(),
                        value = NBValueItem.Texts(
                            humidityValue.displayValue,
                            humidityValue.unit
                        )
                    )
                )
            }

            nbNullSafe(dewPointTemperature) { dewPointTemperatureValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_dew_point),
                        valueIcon = NBIcons.DewPoint.toValueIcon(),
                        value = dewPointTemperatureValue.toValueItemWithUnit(units)
                    )
                )
            }

            nbNullSafe(cloudiness) { cloudinessValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_cloudiness),
                        valueIcon = NBIcons.Cloudiness.toValueIcon(),
                        value = NBValueItem.Texts(
                            cloudinessValue.displayValue,
                            cloudinessValue.unit
                        )
                    )
                )
            }

            nbNullSafe(uvIndex) { uvIndexValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_uv_index),
                        valueIcon = NBIcons.UVIndex.toValueIcon(),
                        value = NBValueItem.Texts(
                            uvIndexValue.displayValue,
                            uvIndexValue.unit
                        )
                    )
                )
            }

            nbNullSafe(visibility) { visibilityValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_visibility),
                        valueIcon = NBIcons.Visibility.toValueIcon(),
                        value = NBValueItem.Texts(
                            visibilityValue.displayValue,
                            visibilityValue.unit
                        )
                    )
                )
            }

            return nbNullSafeList(
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