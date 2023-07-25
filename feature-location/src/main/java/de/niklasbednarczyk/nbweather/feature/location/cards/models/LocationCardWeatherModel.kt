package de.niklasbednarczyk.nbweather.feature.location.cards.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueIconModel.Companion.toValueIcon
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.data.onecall.values.units.DistanceValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PercentValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PressureValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.ProbabilityValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.UVIndexValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.WindSpeedValue
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesValue
import de.niklasbednarczyk.nbweather.feature.location.extensions.displayText

data class LocationCardWeatherModel(
    override val cardTitle: NBString?,
    val items: List<NBGridItem.ThreeLines?>
) : LocationCardItem {

    companion object {

        fun from(
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
                        value = NBValueItem.Units(probabilityOfPrecipitationValue)
                    )
                )
            }

            nbNullSafe(rain1hVolume) { rain1hVolumeValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_rain),
                        valueIcon = NBIcons.Rain.toValueIcon(),
                        value = NBValueItem.Units(rain1hVolumeValue)
                    )
                )
            }

            nbNullSafe(snow1hVolume) { snow1hVolumeValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_snow),
                        valueIcon = NBIcons.Snow.toValueIcon(),
                        value = NBValueItem.Units(snow1hVolumeValue)
                    )
                )
            }

            nbNullSafe(windSpeed) { windSpeedValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_wind_speed),
                        valueIcon = NBIcons.WindSpeed.toValueIcon(),
                        value = NBValueItem.Units(windSpeedValue)
                    )
                )
            }

            nbNullSafe(windGust) { windGustValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_wind_gust),
                        valueIcon = NBIcons.WindGust.toValueIcon(),
                        value = NBValueItem.Units(windGustValue)
                    )
                )
            }

            nbNullSafe(windDegrees) { windDegreesValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_wind_direction),
                        valueIcon = NBIcons.WindDegrees.toValueIcon(windDegreesValue.rotationDegrees),
                        value = NBValueItem.Text(
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
                        value = NBValueItem.Units(pressureValue)
                    )
                )
            }

            nbNullSafe(humidity) { humidityValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_humidity),
                        valueIcon = NBIcons.Humidity.toValueIcon(),
                        value = NBValueItem.Units(humidityValue)
                    )
                )
            }

            nbNullSafe(dewPointTemperature) { dewPointTemperatureValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_dew_point),
                        valueIcon = NBIcons.DewPoint.toValueIcon(),
                        value = NBValueItem.Units(
                            unitsValue = dewPointTemperatureValue.getLong()
                        )
                    )
                )
            }

            nbNullSafe(cloudiness) { cloudinessValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_cloudiness),
                        valueIcon = NBIcons.Cloudiness.toValueIcon(),
                        value = NBValueItem.Units(cloudinessValue)
                    )
                )
            }

            nbNullSafe(uvIndex) { uvIndexValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_uv_index),
                        valueIcon = NBIcons.UVIndex.toValueIcon(),
                        value = NBValueItem.Units(uvIndexValue)
                    )
                )
            }

            nbNullSafe(visibility) { visibilityValue ->
                items.add(
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_weather_value_visibility),
                        valueIcon = NBIcons.Visibility.toValueIcon(),
                        value = NBValueItem.Units(visibilityValue)
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